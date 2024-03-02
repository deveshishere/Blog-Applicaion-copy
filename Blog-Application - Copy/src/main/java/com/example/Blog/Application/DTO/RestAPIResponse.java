package com.example.Blog.Application.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestAPIResponse {

    private String userId;
    private int id;
    private String title;
    private String completed;
}
