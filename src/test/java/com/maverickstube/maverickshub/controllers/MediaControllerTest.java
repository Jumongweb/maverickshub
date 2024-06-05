package com.maverickstube.maverickshub.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maverickstube.maverickshub.dtos.requests.UploadMediaRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.maverickstube.maverickshub.utils.TestUtils.TEST_VIDEO_LOCATION;
import static com.maverickstube.maverickshub.utils.TestUtils.buildUploadMediaRequest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MediaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testUploadMedia() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        try(InputStream inputStream = Files.newInputStream(Path.of(TEST_VIDEO_LOCATION))){
            UploadMediaRequest uploadMediaRequest = buildUploadMediaRequest(inputStream);
            mockMvc.perform(post("/api/v1/media")
                    .contentType(MediaType.APPLICATION_JSON)

                    .content(mapper.writeValueAsBytes(uploadMediaRequest)))
                    .andExpect(status().isCreated())
                    .andDo(print());
        } catch (Exception exception) {
            throw exception;
        }
    }
}
