package com.maverickstube.maverickshub.services;

import com.github.fge.jsonpatch.JsonPatch;
import com.maverickstube.maverickshub.dtos.requests.UpdateMediaRequest;
import com.maverickstube.maverickshub.dtos.requests.UploadMediaRequest;
import com.maverickstube.maverickshub.dtos.response.MediaResponse;
import com.maverickstube.maverickshub.dtos.response.UpdateMediaResponse;
import com.maverickstube.maverickshub.dtos.response.UploadMediaResponse;
import com.maverickstube.maverickshub.models.Media;

import java.util.List;

public interface MediaService {
    UploadMediaResponse upload(UploadMediaRequest request);
    Media getMediaById(long l);

    UpdateMediaResponse updateMedia(Long mediaId, JsonPatch updateMediaRequest);

    List<MediaResponse> getMediaFor(Long userId);
}
