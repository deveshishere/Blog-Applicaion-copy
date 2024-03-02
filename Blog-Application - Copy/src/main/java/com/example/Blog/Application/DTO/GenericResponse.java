package com.example.Blog.Application.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse <T>{

    public String message;
    public boolean success;
    public int code;
    @JsonInclude(JsonInclude.Include. NON_NULL)
    public T data;
}
