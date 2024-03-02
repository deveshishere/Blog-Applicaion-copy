package com.example.Blog.Application.responseClass;

import lombok.Data;

@Data
public class UserResponse {

    private int Id;
    private String  About;
    private String  Email;
    private String  UserName;
    private String  Password;
    private String  Content;
    private String  Date;
}
