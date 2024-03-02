package com.example.Blog.Application.services.serviceImpl;

import com.example.Blog.Application.DTO.GenericResponse;
import com.example.Blog.Application.DTO.PostDto;
import com.example.Blog.Application.entities.Category;
import com.example.Blog.Application.entities.Post;
import com.example.Blog.Application.entities.User;
import com.example.Blog.Application.exception.ResourceNotFoundException;
import com.example.Blog.Application.repositories.readOnlyRepo.CategoryRepo;
import com.example.Blog.Application.repositories.readOnlyRepo.PostRepo;
import com.example.Blog.Application.repositories.readOnlyRepo.UserRepo;
import com.example.Blog.Application.responseClass.UserResponse;
import com.example.Blog.Application.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;
@Slf4j
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public PostDto createPost(PostDto postDto) {
        User user = this.userRepo.findById(postDto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("user ", " user id", postDto.getUserId()));
        Category category = this.categoryRepo.findById(postDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("category ", " category id", postDto.getCategoryId()));
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setPostDate(new Date());
        post.setImageName("default.png");
        post.setUser(user);
        post.setCategory(category);
        Post save = this.postRepo.save(post);
        PostDto postSaved = this.modelMapper.map(save, PostDto.class);
        log.info("Data Got saved");
        return postSaved;
    }

    @Override
    public PostDto updatePost(PostDto postDto) {
        Post post = this.postRepo.findById(postDto.getPostId()).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postDto.getPostId()));
        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        post.setImageName(postDto.getImageName());
        Post save = this.postRepo.save(post);
        return this.modelMapper.map(save,PostDto.class);
    }

    @Override
    public void deletePost(PostDto id) {
        Post post = this.postRepo.findById(id.getPostId()).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", id.getPostId()));
        this.postRepo.delete(post);
    }

    @Override
    public List<PostDto> getAllPosts(PostDto request) {
        Pageable p = PageRequest.of(request.getPageNumber(), request.getPageSize());
        Page<Post> posts = this.postRepo.findAll(p);
        List<Post> allPosts = posts.getContent();
        List<PostDto> collect = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public PostDto getSinglePost(PostDto id) {
        Post post = this.postRepo.findById(id.getPostId()).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", id.getPostId()));
        PostDto postDto = this.modelMapper.map(post, PostDto.class);
        return postDto;
    }

    @Override
    public List<PostDto> getPostByCategory(Integer id) {

        Category category = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", id));
        List<Post> posts = this.postRepo.findByCategory(category);
        List<PostDto> collect = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " usre id", userId));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDto> postDto = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDto;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = postRepo.findByTitleContaining(keyword);
        List<PostDto> postDto = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDto;
    }


    @Override
    public ResponseEntity<GenericResponse> getAllData(PostDto request) {
        log.info("Request for getAlldata"+request);
        GenericResponse response = new GenericResponse();
        List<UserResponse> push = new ArrayList<>();
        User user = this.userRepo.findById(request.getUserId()).orElseThrow(() -> new ResourceNotFoundException("user ", " user id", request.getUserId()));
        try {
            if (Objects.nonNull(user)) {
                List<PostRepo.GetData> allPostData = this.postRepo.getAllPostData(request.getUserId());
//                List<Post> all = this.postRepo.findAll();
                if (!CollectionUtils.isEmpty(allPostData)) {
                    for (PostRepo.GetData e : allPostData) {
                        UserResponse data = new UserResponse();
                        data.setId(e.getId());
                        data.setUserName(e.getUserName());
                        data.setContent(e.getContent());
                        data.setAbout(e.getAbout());
                        data.setEmail(e.getEmail());
                        data.setPassword(e.getPassword());
                        data.setDate(e.getDate());
                        push.add(data);
                    }
                }
                if (!CollectionUtils.isEmpty(push)) {
                    response.setCode(HttpStatus.OK.value());
                    response.setSuccess(true);
                    response.setMessage("Data Fetched Successfully!!");
                    response.setData(push);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    response.setCode(HttpStatus.NOT_FOUND.value());
                    response.setSuccess(false);
                    response.setData(Collections.emptyList());
                    response.setMessage("unable to fetch data");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }else{
                response.setCode(HttpStatus.NOT_FOUND.value());
                response.setData(Collections.emptyList());
                response.setMessage("User is not exist and user Id is :" + request.getUserId());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }catch (Exception ex) {
            log.error("Exception occurred while getAllData :{}", ex.getMessage());
            response.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            response.setSuccess(false);
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(Collections.emptyList());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
