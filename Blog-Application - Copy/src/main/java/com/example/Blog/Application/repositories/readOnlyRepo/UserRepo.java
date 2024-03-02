package com.example.Blog.Application.repositories.readOnlyRepo;

import com.example.Blog.Application.configuration.ReadOnlyRepository;
import com.example.Blog.Application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    @Modifying
    @Transactional
    @Query(value = "insert into user (user_name,email,password,about) values (:name,:email,:password:,:about)",nativeQuery = true)
    Integer createUser(@Param("name") String name,
                       @Param("email") String email,
                       @Param("password") String password,
                       @Param("about") String about);

    Optional<User> findByEmail(String email);
}
