package com.example.Blog.Application.userTestClass;

import com.example.Blog.Application.DTO.GenericResponse;
import com.example.Blog.Application.DTO.PostDto;
import com.example.Blog.Application.DTO.UserDto;
import com.example.Blog.Application.entities.User;
import com.example.Blog.Application.repositories.readOnlyRepo.PostRepo;
import com.example.Blog.Application.repositories.readOnlyRepo.UserRepo;
import com.example.Blog.Application.repositories.readOnlyRepo.implClass.GetDataImpl;
import com.example.Blog.Application.services.UserService;
import com.example.Blog.Application.services.serviceImpl.PostServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
public class UserTestClass {

    @MockBean
    private UserRepo userRepo;
    @MockBean
    private PostRepo postRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private PostServiceImpl postService;

    @Test
    public void createUser() {
        UserDto request = new UserDto();
        request.setId(1);
        request.setEmail("email");
        request.setPassword("pass");
        request.setName("dev");
        request.setAbout("about");
        Mockito.when(userRepo.save(any())).thenReturn(null);
        UserDto user = userService.createUser(request);
        assertEquals(1, user.getId());
    }

    @Test
    public void getAllPostData() {
        PostDto request1 = new PostDto();
        User request = new User();
        request.setId(1);
        request.setEmail("email");
        request.setPassword("pass");
        request.setName("dev");
        request.setAbout("about");
        List<PostRepo.GetData> list = new ArrayList<>();
        list.add(new GetDataImpl(1,"hi","hi","h","h","h","h"));
        Mockito.when(userRepo.findById(any())).thenReturn(Optional.of(request));
        Mockito.when(postRepo.getAllPostData(anyInt())).thenReturn(list);
        ResponseEntity<GenericResponse> allData = postService.getAllData(request1);
        assertEquals("Data Fetched Successfully!!",allData.getBody().getMessage());
    }

    @Test
    public void getAllPostWithNoData() {
        PostDto request1 = new PostDto();
        User request = new User();
        List<PostRepo.GetData> list = new ArrayList<>();
        Mockito.when(userRepo.findById(any())).thenReturn(Optional.of(request));
        Mockito.when(postRepo.getAllPostData(anyInt())).thenReturn(list);
        ResponseEntity<GenericResponse> allData = postService.getAllData(request1);
        assertEquals(404,allData.getBody().getCode());
    }

    @Test
    public void getAllPostDataWithException() {
        PostDto request1 = new PostDto();
        User request = new User();
        List<PostRepo.GetData> list = new ArrayList<>();
        Mockito.when(userRepo.findById(any())).thenReturn(Optional.of(request));
        Mockito.when(postRepo.getAllPostData(anyInt())).thenThrow(new RuntimeException("Test Exception"));
        ResponseEntity<GenericResponse> allData = postService.getAllData(request1);
        assertEquals(500,allData.getBody().getCode());
    }

}
