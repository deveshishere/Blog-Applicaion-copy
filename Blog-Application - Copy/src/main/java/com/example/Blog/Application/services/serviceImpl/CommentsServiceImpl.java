package com.example.Blog.Application.services.serviceImpl;

import com.example.Blog.Application.DTO.CategoryDto;
import com.example.Blog.Application.DTO.CommentsDto;
import com.example.Blog.Application.DTO.PostDto;
import com.example.Blog.Application.entities.Comments;
import com.example.Blog.Application.entities.Post;
import com.example.Blog.Application.exception.ResourceNotFoundException;
import com.example.Blog.Application.repositories.readOnlyRepo.CommentRepo;
import com.example.Blog.Application.repositories.readOnlyRepo.PostRepo;
import com.example.Blog.Application.services.CommentsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;



    @Override
    public CommentsDto createComments(CommentsDto commentsDto) {

        Post post = postRepo.findById(commentsDto.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException("postId", "post id", commentsDto.getPostId()));
        Comments comments = this.modelMapper.map(commentsDto, Comments.class);
        comments.setPost(post);
        Comments savedComment = commentRepo.save(comments);
        return this.modelMapper.map(savedComment, CommentsDto.class);
    }

    @Override
    public void deleteComments(CommentsDto commentsDto) {
        Comments comments = commentRepo.findById(commentsDto.getPostId()).orElseThrow(() -> new ResourceNotFoundException("postId", "post id", commentsDto.getPostId()));
        commentRepo.delete(comments);
    }
}
