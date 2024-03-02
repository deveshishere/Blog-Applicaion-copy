package com.example.Blog.Application.controllers;

import com.example.Blog.Application.DTO.CommentsDto;
import com.example.Blog.Application.DTO.GenericResponse;
import com.example.Blog.Application.services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Blog-Application/comments/")
public class CommentsController {
    @Autowired
    private CommentsService commentsService;

    @RequestMapping("createComments")
    public ResponseEntity<?> createComments(@RequestBody CommentsDto commentsDto){
        CommentsDto comments = commentsService.createComments(commentsDto);
        return new ResponseEntity<>(new GenericResponse<>("Comment created Successfully !!!",true, HttpStatus.CREATED.value(), comments),HttpStatus.CREATED);
    }

    @RequestMapping("deleteComments")
    public ResponseEntity<?> deleteComments(@RequestBody CommentsDto commentsDto){
        commentsService.deleteComments(commentsDto);
        return new ResponseEntity<>(new GenericResponse<>("Deleted Successfully !!",true,HttpStatus.OK.value(),null),HttpStatus.OK);
    }

}
