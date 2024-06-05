package com.maverickstube.maverickshub.dtos.requests;

import com.maverickstube.maverickshub.models.Category;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdateMediaRequest {
    private Long mediaId;
    private String url;
    private String description;
    private Category category;
}
