package com.example.Blog.Application.repositories.readOnlyRepo;

import com.example.Blog.Application.entities.Category;
import com.example.Blog.Application.entities.Post;
import com.example.Blog.Application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    @Query(value = "select u.id,u.about,u.email,u.user_name AS userName,u.password,p.content AS content,DATE_FORMAT(p.post_date, '%W %D %M %Y') AS date from posts p JOIN user u on p.user_id=u.id where u.id=:id",nativeQuery = true)
    List<GetData> getAllPostData(@Param("id") Integer id);

    interface GetData{

        int getId();
        String getAbout();
        String getEmail();
        String getUserName();
        String getPassword();
        String getContent();
        String getDate();
    }

    List<Post> findByTitleContaining(String str);
}
