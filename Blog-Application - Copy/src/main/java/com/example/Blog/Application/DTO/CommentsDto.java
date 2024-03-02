package com.example.Blog.Application.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CommentsDto {

    private int id;
    private String content;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int postId;
}
