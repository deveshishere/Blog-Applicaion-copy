package com.example.Blog.Application.controllers;

import com.example.Blog.Application.DTO.GenericResponse;
import com.example.Blog.Application.DTO.PostDto;
import com.example.Blog.Application.services.FileService;
import com.example.Blog.Application.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/Blog-Application/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

//    @Value("${project.image}")
//    private String path;

    @PostMapping("createPost")
    public ResponseEntity<?> createPost(@RequestBody PostDto postDto) {
        this.postService.createPost(postDto);
        return new ResponseEntity<>(new GenericResponse<>("Post created successfully", true, HttpStatus.OK.value(), null), HttpStatus.CREATED);
    }

    // Get by user
    @PostMapping("getPostByUser")
    public ResponseEntity<?> getPostByUser(@RequestBody PostDto postDto) {
        List<PostDto> postByUser = this.postService.getPostByUser(postDto.getUserId());
        return new ResponseEntity<>(new GenericResponse<>("Fetched", true, HttpStatus.OK.value(), postByUser), HttpStatus.OK);
    }

    // Get by category
    @PostMapping("getPostByCategory")
    public ResponseEntity<?> getPostByCategory(@RequestBody PostDto postDto) {
        List<PostDto> postByCategory = this.postService.getPostByCategory(postDto.getCategoryId());
        return new ResponseEntity<>(new GenericResponse<>("Fetched", true, HttpStatus.OK.value(), postByCategory), HttpStatus.OK);
    }

    // Get single post
    @PostMapping("getSinglePost")
    public ResponseEntity<?> getSinglePost(@RequestBody PostDto postDto) {
        PostDto singlePost = this.postService.getSinglePost(postDto);
        return new ResponseEntity<>(new GenericResponse<>("Fetched", true, HttpStatus.OK.value(), singlePost), HttpStatus.OK);
    }

    // Get All post
    @PostMapping("getAllPost")
    public ResponseEntity<?> getAllPost(@RequestBody PostDto request) {
        List<PostDto> postByCategory = this.postService.getAllPosts(request);
        return new ResponseEntity<>(new GenericResponse<>("Fetched", true, HttpStatus.OK.value(), postByCategory), HttpStatus.OK);
    }

    // update
    @PostMapping("updatePost")
    public ResponseEntity<?> updatePost(@Valid @RequestBody PostDto request) {
        PostDto postDto = this.postService.updatePost(request);
        return new ResponseEntity<>(new GenericResponse<>("Data Updated Successfully", true, HttpStatus.OK.value(), null), HttpStatus.OK);
    }

    // Delete
    @PostMapping("deletePost")
    public ResponseEntity<?> deletePost(@RequestBody PostDto request) {
        this.postService.deletePost(request);
        return new ResponseEntity<>(new GenericResponse<>("Data Deleted Successfully", true, HttpStatus.OK.value(), null), HttpStatus.OK);
    }

    @PostMapping("getAllPostData")
    public ResponseEntity<?> getAllPostDataWithUserId(@RequestBody PostDto postDto) {
        return postService.getAllData(postDto);
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchPosts(@RequestBody PostDto postDto) {
        List<PostDto> postDtos = postService.searchPosts(postDto.getKeyword());
        return new ResponseEntity<>(new GenericResponse<>("Fetched", true, HttpStatus.OK.value(), postDtos), HttpStatus.OK);
    }
//
//    @PostMapping("/image/upload/{postId}")
//    public ResponseEntity<?> uploadPostImage(@RequestParam("image") MultipartFile image,
//                                             @PathVariable Integer postId) throws IOException {
//
//        PostDto id = new PostDto();
//        id.setPostId(postId);
//        PostDto singlePost = this.postService.getSinglePost(id);
//        String fileName = this.fileService.uploadImage(path, image);
//        singlePost.setImageName(fileName);
//        PostDto updatedPost = this.postService.updatePost(singlePost);
//        return new ResponseEntity<>(new GenericResponse<>("uploaded Successsfully !!!", true, HttpStatus.OK.value(), updatedPost), HttpStatus.OK);
//
//
//    }
//
//    @GetMapping(value = "/image/download/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
//    public void downloadImage(@PathVariable("imageName") String imageName,
//                              HttpServletResponse response) throws IOException {
//        InputStream resource = this.fileService.getResource(path, imageName);
//        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
//        StreamUtils.copy(resource,response.getOutputStream());
//    }
}
