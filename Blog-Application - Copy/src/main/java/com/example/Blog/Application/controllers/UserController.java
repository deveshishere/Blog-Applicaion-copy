package com.example.Blog.Application.controllers;

import com.example.Blog.Application.DTO.GenericResponse;
import com.example.Blog.Application.DTO.UserDto;
import com.example.Blog.Application.configuration.AdminClient;
import com.example.Blog.Application.entities.DropdownFilterRequest;
import com.example.Blog.Application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Blog-Application/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    public AdminClient adminClient;


    // working
    @PostMapping("createUser")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto user = this.userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
   // working
    @PostMapping("updateUser")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            // If there are validation errors, construct error messages for each field
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("{ ");
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errorMessage.append(fieldError.getField()).append("=").append(fieldError.getDefaultMessage()).append(", ");
            }
            // Remove the trailing comma and space
            if (errorMessage.length() > 2) {
                errorMessage.setLength(errorMessage.length() - 2);
            }
            errorMessage.append(" }");

            // Return response with formatted error messages
            return ResponseEntity.badRequest().body(new GenericResponse<>(errorMessage.toString(), false, HttpStatus.BAD_REQUEST.value(), null));
        }
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
    public ResponseEntity<GenericResponse> getAllUsers(){
        return userService.getAllUsers();
    }
    // working
    @PostMapping("deleteUser")
    public ResponseEntity<GenericResponse> deleteUser(@RequestBody UserDto userDto){
       this.userService.deleteUser(userDto.getId());
        return new ResponseEntity<>(new GenericResponse("User Deleted Successfully", true,HttpStatus.OK.value(), null), HttpStatus.OK);
    }





    @PostMapping("/restAPI")
    public ResponseEntity<?> fetch(@RequestBody DropdownFilterRequest request){
        return adminClient.dropdownFilter(request);
    }
}
