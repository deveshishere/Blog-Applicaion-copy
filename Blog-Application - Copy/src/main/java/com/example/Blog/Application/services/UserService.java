package com.example.Blog.Application.services;

import com.example.Blog.Application.DTO.GenericResponse;
import com.example.Blog.Application.DTO.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,Integer userId);
    UserDto getUSerBtId(Integer userId);
    ResponseEntity<GenericResponse> getAllUsers();
    void deleteUser(Integer UserId);

//    ResponseEntity<GenericResponse> fetchAPI();


}
