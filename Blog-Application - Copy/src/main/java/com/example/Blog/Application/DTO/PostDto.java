package com.example.Blog.Application.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.*;

@Data
public class PostDto {

    private int postId;
    @NotEmpty
    private String title;
    @Size(min = 4,message = "Content must have 4 character")
    private String content;
    private String imageName;
    private Date date;
    private CategoryDto category;
    private UserDto user;
    private List<CommentsDto> comments = new ArrayList<>();
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int userId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int categoryId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int pageSize;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int pageNumber;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String keyword;

}
