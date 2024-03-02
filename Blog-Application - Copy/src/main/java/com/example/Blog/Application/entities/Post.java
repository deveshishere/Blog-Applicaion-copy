package com.example.Blog.Application.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Entity
@Data
@Getter
@Setter
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int postId;

    @NotEmpty
    @Column(name = "post_title", length = 100, nullable = false)
    private String title;

    private String content;

    @Column(name = "image_name")
    private String imageName;

    @ManyToOne
    private User user;

    @Column(name = "post_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date postDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Comments> comments = new ArrayList<>();

}
