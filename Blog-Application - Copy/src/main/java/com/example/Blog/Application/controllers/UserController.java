package com.example.Blog.Application.controllers;

import com.example.Blog.Application.DTO.GenericResponse;
import com.example.Blog.Application.DTO.UserDto;
import com.example.Blog.Application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/Blog-Application/")
public class UserController {

    @Autowired
    private UserService userService;


    // working
    @PostMapping("createUser")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto user = this.userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
   // working
    @PostMapping("updateUser")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDto userDto){
        this.userService.updateUser(userDto, userDto.getId());
        return new ResponseEntity<>(new GenericResponse("User Updated Successfully", true,HttpStatus.OK.value(), null), HttpStatus.OK);
    }

    // working
    @PostMapping("getSingleUser")
    public ResponseEntity<?> getUSerBtId(@RequestBody UserDto uid){
        return ResponseEntity.ok(this.userService.getUSerBtId(uid.getId()));
    }
    // working
    @PostMapping("getAllUsers")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(new GenericResponse<>("Fetched",true,HttpStatus.OK.value(),this.userService.getAllUsers()), HttpStatus.OK);
    }
    // working
    @PostMapping("deleteUser")
    public ResponseEntity<?> deleteUser(@RequestBody UserDto userDto){
       this.userService.deleteUser(userDto.getId());
        return new ResponseEntity<>(new GenericResponse("User Deleted Successfully", true,HttpStatus.OK.value(), null), HttpStatus.OK);
    }

//
//    @PostMapping("/restAPI")
//    public ResponseEntity<?> fetch(){
//        return userService.fetchAPI();
//    }
}
