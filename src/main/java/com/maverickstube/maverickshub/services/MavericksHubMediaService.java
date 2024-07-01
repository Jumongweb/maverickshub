package com.maverickstube.maverickshub.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.maverickstube.maverickshub.dtos.requests.UploadMediaRequest;
import com.maverickstube.maverickshub.dtos.response.MediaResponse;
import com.maverickstube.maverickshub.dtos.response.UpdateMediaResponse;
import com.maverickstube.maverickshub.dtos.response.UploadMediaResponse;
import com.maverickstube.maverickshub.exceptions.MediaNotFoundException;
import com.maverickstube.maverickshub.exceptions.MediaUpdateFailedException;
import com.maverickstube.maverickshub.exceptions.MediaUploadFailedException;
import com.maverickstube.maverickshub.models.Media;
import com.maverickstube.maverickshub.models.User;
import com.maverickstube.maverickshub.repositories.MediaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class MavericksHubMediaService implements MediaService{
    private final MediaRepository mediaRepository;
    private final Cloudinary cloudinary;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Override
    public UploadMediaResponse upload(UploadMediaRequest request) {
        User user = userService.getById(request.getUserId());
        try {

            Uploader uploader = cloudinary.uploader();
            Map<?, ?> response = uploader.upload(request.getMediaFile().getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            String url = response.get("url").toString();
            Media media = modelMapper.map(request, Media.class);
            media.setUrl(url);
            media = mediaRepository.save(media);
            return modelMapper.map(media, UploadMediaResponse.class);
        } catch (IOException exception) {
            throw new MediaUploadFailedException("media upload failed");
        }
    }

    @Override
    public Media getMediaById(long id) {
        return mediaRepository.findById(id)
                .orElseThrow(()->new MediaNotFoundException("Media not found"));
    }

    @Override
    public UpdateMediaResponse updateMedia(Long mediaId,
                                           JsonPatch jsonPatch){
        try {
            Media media = getMediaById(mediaId);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode mediaNode = objectMapper.convertValue(media, JsonNode.class);

            mediaNode = jsonPatch.apply(mediaNode);
            media = objectMapper.convertValue(mediaNode, Media.class);
            mediaRepository.save(media);
            return modelMapper.map(media, UpdateMediaResponse.class);
        } catch (JsonPatchException exception){
            throw new MediaUpdateFailedException("update failed ");
        }
    }

    @Override
    public List<MediaResponse> getMediaFor(Long userId) {
        List<Media> media = mediaRepository.findAllMediaFor(userId);
        return media.stream()
                .map(m->modelMapper.map(m, MediaResponse.class))
                .toList();
    }


}
