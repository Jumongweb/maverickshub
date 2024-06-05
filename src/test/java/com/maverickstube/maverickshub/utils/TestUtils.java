package com.maverickstube.maverickshub.utils;

import com.maverickstube.maverickshub.dtos.requests.UploadMediaRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static com.maverickstube.maverickshub.models.Category.ACTION;

public class TestUtils {
    public static final String TEST_IMAGE_LOCATION = "C:\\Users\\DELL\\Desktop\\maverickshub\\src\\main\\resources\\static\\flash.jpeg";
    public static final String TEST_VIDEO_LOCATION = "C:\\Users\\DELL\\Desktop\\maverickshub\\src\\main\\resources\\static\\Understanding Different Layers in Spring Boot_ Layered Architecture Explained.mp4";

    public static UploadMediaRequest buildUploadMediaRequest(InputStream inputStream) throws IOException {
        UploadMediaRequest request = new UploadMediaRequest();
        MultipartFile file = new MockMultipartFile("media", inputStream);
        request.setMediaFile(file);
        request.setUserId(200L);
        request.setCategory(ACTION);
        return request;
    }
}
