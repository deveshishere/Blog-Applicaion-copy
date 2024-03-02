package com.example.Blog.Application.repositories.readOnlyRepo;

import com.example.Blog.Application.entities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comments,Integer> {
}
