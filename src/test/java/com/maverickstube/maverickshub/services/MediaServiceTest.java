package com.maverickstube.maverickshub.services;

import com.maverickstube.maverickshub.dtos.requests.UpdateMediaRequest;
import com.maverickstube.maverickshub.dtos.requests.UploadMediaRequest;
import com.maverickstube.maverickshub.dtos.response.UpdateMediaResponse;
import com.maverickstube.maverickshub.dtos.response.UploadMediaResponse;
import com.maverickstube.maverickshub.models.Media;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.maverickstube.maverickshub.models.Category.ACTION;
import static com.maverickstube.maverickshub.models.Category.STEP_MOM;
import static com.maverickstube.maverickshub.utils.TestUtils.TEST_VIDEO_LOCATION;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Slf4j
@Sql(scripts = {"/db/data.sql"})
public class MediaServiceTest {
    @Autowired
    private MediaService mediaService;

    @Test
    public void uploadMediaTest(){
        Path path = Paths.get(TEST_VIDEO_LOCATION);
        try(var inputStream = Files.newInputStream(path)){
            UploadMediaRequest request = buildUploadMediaRequest(inputStream);
            // touched here
            request.setUserId(200L);
            UploadMediaResponse response = mediaService.upload(request);
            log.info("response ==> {}", response);
            assertThat(response).isNotNull();
            assertThat(response.getUrl()).isNotNull();
        } catch (IOException e) {
            assertThat(e).isNotNull();
        }
    }

    @Test
    @DisplayName("test that media can be retrieved by id")
    public void testGetMedia(){
        Media media = mediaService.getMediaById(100L);
        log.info("found content----> {}", media);
        assertThat(media.getId()).isNotNull();
    }

    private static UploadMediaRequest buildUploadMediaRequest(InputStream inputStream) throws IOException {
        UploadMediaRequest request = new UploadMediaRequest();
        MultipartFile file = new MockMultipartFile("media", inputStream);
        request.setMediaFile(file);
        request.setUserId(200L);
        request.setCategory(ACTION);
        return request;
    }

//    @Test
//    @DisplayName("test that media can be update")
//    public void testUpdateMedia(){
//        Media media = mediaService.getMediaById(100L);
//        assertThat(media.getCategory()).isEqualTo(ACTION);
//        UpdateMediaRequest updateMediaRequest = new UpdateMediaRequest();
//        updateMediaRequest.setCategory(STEP_MOM);
//        updateMediaRequest.setMediaId(100L);
//
//        var response = mediaService.updateMedia(updateMediaRequest);
//        assertThat(response).isNotNull();
//        media = mediaService.getMediaById(100L);
//        assertThat(media.getCategory()).isEqualTo(STEP_MOM);
//    }

    @Test
    @DisplayName("test update media files")
    public void testUpdateUser(){
        UpdateMediaRequest request = new UpdateMediaRequest();
        request.setCategory(STEP_MOM);
        request.setDescription("test description");
        UpdateMediaResponse response = new UpdateMediaResponse();
        mediaService.updateMedia(103L, request);

    }

}
