package com.example.Blog.Application.responseClass;

import com.example.Blog.Application.DTO.UserDto;
import lombok.Data;

@Data
public class JwtAuthResponse {

	private String token;
	
	private UserDto user;
}
