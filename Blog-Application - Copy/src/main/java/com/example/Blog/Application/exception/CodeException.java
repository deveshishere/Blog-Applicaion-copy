package com.example.Blog.Application.exception;

import lombok.Data;

@Data
public class CodeException extends Exception{

    private String error;

    CodeException(){

    }

    public CodeException(String error) {
        this.error = error;
    }
    public CodeException(String error,int code) {
        this.error = error;
    }
}
