package com.example.Blog.Application.repositories.readOnlyRepo;

import com.example.Blog.Application.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
