package com.example.Blog.Application.services.serviceImpl;

import com.example.Blog.Application.DTO.CategoryDto;
import com.example.Blog.Application.entities.Category;
import com.example.Blog.Application.exception.ResourceNotFoundException;
import com.example.Blog.Application.repositories.readOnlyRepo.CategoryRepo;
import com.example.Blog.Application.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
//        if (category.getCategoryDescription().contains("   ")){
//            category.getCategoryDescription().replaceAll("", '"');
//        }
        Category save = this.categoryRepo.save(category);
        return this.modelMapper.map(save,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
        Category category = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category ", " Category Id", id));
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        Category save = this.categoryRepo.save(category);
        return this.modelMapper.map(save,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryDto) {
        Category category = this.categoryRepo.findById(categoryDto).orElseThrow(() -> new ResourceNotFoundException("Category ", " Category Id", categoryDto));
        this.categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategory(CategoryDto categoryDto) {
        Category category = this.categoryRepo.findById(categoryDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category ", " Category Id", categoryDto.getCategoryId()));
        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> category = this.categoryRepo.findAll();
        List<CategoryDto> collect = category.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
        return collect;
    }
}
