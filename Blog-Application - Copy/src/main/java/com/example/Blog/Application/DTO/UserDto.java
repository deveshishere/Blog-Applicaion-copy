package com.example.Blog.Application.DTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@JsonPropertyOrder({"id","name","email","password","about"})
public class UserDto {

    @JsonProperty("id")
    private int id;

    @NotBlank(message = "Name can not be empty ")
    private String name;

    @Email
    @Size(min = 4,message = "Email must contain 4 char ")
    private String email;

    @NotBlank(message = "Password must contain 4 char ")
    private String password;

    private String about;
}
