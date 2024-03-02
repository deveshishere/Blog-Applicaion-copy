package com.example.Blog.Application.services;

import com.example.Blog.Application.DTO.CommentsDto;

public interface CommentsService {

    CommentsDto createComments(CommentsDto categoryDto);

    void deleteComments(CommentsDto categoryDto);
}
