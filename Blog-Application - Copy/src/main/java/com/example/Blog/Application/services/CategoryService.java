package com.example.Blog.Application.services;

import com.example.Blog.Application.DTO.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, Integer id);

    void deleteCategory(Integer categoryDto);

    CategoryDto getCategory(CategoryDto categoryDto);

    List<CategoryDto> getAllCategory();
}
