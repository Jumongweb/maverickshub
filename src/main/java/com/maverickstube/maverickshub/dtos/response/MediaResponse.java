package com.maverickstube.maverickshub.dtos.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.maverickstube.maverickshub.models.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class MediaResponse {
    private Long id;
    private String url;
    private String description;
    private Category category;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDateTime timeCreated;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDateTime timeUpdated;
    private UserResponse uploader;
}
