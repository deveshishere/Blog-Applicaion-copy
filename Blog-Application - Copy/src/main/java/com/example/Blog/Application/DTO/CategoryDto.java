package com.example.Blog.Application.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CategoryDto {

    private int categoryId;
    @NotBlank
    @Size(min =4,message = "Bhai 4 shabd se zada ka Title lunga mai")
    private String categoryTitle;
    @NotBlank
    @Size(min=4,message = "Bahen 4 shabd se zada ka kuch type karlo")
    private String categoryDescription;
}
