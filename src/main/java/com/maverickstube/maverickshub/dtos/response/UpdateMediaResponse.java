package com.maverickstube.maverickshub.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maverickstube.maverickshub.models.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class UpdateMediaResponse {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String url;
    @JsonProperty
    private String description;
    private Category category;
    @JsonProperty("created-at")
    private LocalDateTime timeCreated;
    @JsonProperty("updated-at")
    private LocalDateTime timeUpdated;

}
