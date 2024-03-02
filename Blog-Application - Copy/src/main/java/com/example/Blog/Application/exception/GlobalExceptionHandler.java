package com.example.Blog.Application.exception;

import com.example.Blog.Application.DTO.GenericResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.management.relation.RelationNotFoundException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<GenericResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex, WebRequest webRequest){
        String message = ex.getMessage();
        String description = webRequest.getDescription(false);
        String combinedMessage = message + " && " + description;
        GenericResponse response = new GenericResponse(combinedMessage, false,HttpStatus.EXPECTATION_FAILED.value(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validationException(MethodArgumentNotValidException ex){
        Map<String, String> map = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            map.put(fieldName,message);
        });
        return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse> handleAllExceptionHandler(Exception ex, WebRequest webRequest){
        String message = ex.getMessage();
        String description = webRequest.getDescription(false);
        String combinedMessage = message + " -> " + description;
        GenericResponse response = new GenericResponse(combinedMessage, false,HttpStatus.EXPECTATION_FAILED.value(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
