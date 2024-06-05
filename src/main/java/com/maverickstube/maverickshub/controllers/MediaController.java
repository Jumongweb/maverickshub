package com.maverickstube.maverickshub.controllers;

import com.maverickstube.maverickshub.dtos.requests.UploadMediaRequest;
import com.maverickstube.maverickshub.models.Media;
import com.maverickstube.maverickshub.services.MediaService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.apache.tomcat.util.http.fileupload.FileUploadBase.MULTIPART_FORM_DATA;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/media")
@AllArgsConstructor

public class MediaController {

    private final MediaService mediaService;
    @PostMapping(consumes = {MULTIPART_FORM_DATA})
    public ResponseEntity<?> uploadMedia(@ModelAttribute UploadMediaRequest uploadMediaRequest){
        return ResponseEntity.status(CREATED)
                .body(mediaService.upload(uploadMediaRequest));
    }

    @GetMapping("")
    public ResponseEntity<?> getMediaForUser(@RequestParam Long userId){
        return ResponseEntity.ok(mediaService.getMediaById(userId));

    }
}
