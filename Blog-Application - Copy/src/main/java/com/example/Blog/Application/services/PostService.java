package com.example.Blog.Application.services;

import com.example.Blog.Application.DTO.GenericResponse;
import com.example.Blog.Application.DTO.PostDto;
import com.example.Blog.Application.entities.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostDto updatePost(PostDto postDto);

    void deletePost(PostDto id);

    List<PostDto> getAllPosts(PostDto request);

    PostDto getSinglePost(PostDto id);

    List<PostDto> getPostByCategory(Integer id);

    List<PostDto> getPostByUser(Integer id);

    List<PostDto> searchPosts(String keyword);
    ResponseEntity<GenericResponse> getAllData(PostDto postDto);
}
