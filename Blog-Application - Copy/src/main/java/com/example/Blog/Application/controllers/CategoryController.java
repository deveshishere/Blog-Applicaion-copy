package com.example.Blog.Application.controllers;

import com.example.Blog.Application.DTO.CategoryDto;
import com.example.Blog.Application.DTO.GenericResponse;
import com.example.Blog.Application.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Blog-Application/category/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("createCategory")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(this.categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @PostMapping("updateCategory")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryDto categoryDto){
        this.categoryService.updateCategory(categoryDto,categoryDto.getCategoryId());
        return new ResponseEntity<>(new GenericResponse<>("Data Updated Successfully",true,HttpStatus.OK.value(),null), HttpStatus.OK);
    }

    @PostMapping("deleteCategory")
    public ResponseEntity<?> deleteCategory(@RequestBody CategoryDto categoryDto){
        this.categoryService.deleteCategory(categoryDto.getCategoryId());
        return new ResponseEntity<>(new GenericResponse<>("Deleted Category successfully", true,HttpStatus.OK.value(), null), HttpStatus.OK);
    }

    @PostMapping("getCategory")
    public ResponseEntity<?> getCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto category = this.categoryService.getCategory(categoryDto);
        return new ResponseEntity<>(new GenericResponse<>("7 CRORE", true,HttpStatus.OK.value(), category),HttpStatus.OK);
    }

    @PostMapping("getAllCategory")
    public ResponseEntity<?> getAllCategory(){
        List<CategoryDto> category = this.categoryService.getAllCategory();
        return new ResponseEntity<>(new GenericResponse<>("Fetched", true,HttpStatus.OK.value(), category),HttpStatus.OK);
    }

}
