package com.somnath.blogappapis.controllers;

import com.somnath.blogappapis.config.AppConstants;
import com.somnath.blogappapis.entities.Post;
import com.somnath.blogappapis.payloads.ApiResponse;
import com.somnath.blogappapis.payloads.PostDto;
import com.somnath.blogappapis.payloads.PostResponse;
import com.somnath.blogappapis.payloads.UserDto;
import com.somnath.blogappapis.services.FileService;
import com.somnath.blogappapis.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable("userId") Integer userId,@PathVariable("categoryId")Integer categoryId)
    {
        PostDto newPostDto=this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<>(newPostDto, HttpStatus.CREATED);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("categoryId") Integer categoryId)
    {
        List<PostDto> postDtoList=this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable("userId") Integer userId)
    {
        List<PostDto> postDtoList=this.postService.getPostsByUser(userId);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }

    /*@GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts()
    {
        List<PostDto> postDtoList=this.postService.getAllPosts();
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }*/

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir)
    {
        PostResponse postResponse=this.postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer postId)
    {
        PostDto postDto=this.postService.getPostById(postId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable("postId") Integer postId)
    {
        PostDto updatedPostDto=this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPostDto,HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}")
    public ApiResponse deletePost(@PathVariable("postId") Integer postId)
    {
        this.postService.deletePost(postId);
        return new ApiResponse("Post deleted successfully !",true);
    }

    @GetMapping("/post/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords)
    {
        List<PostDto> postDtoList=this.postService.searchPosts(keywords);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }


    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,@PathVariable Integer postId) throws IOException {
        PostDto postDto=this.postService.getPostById(postId);
        String fileName=this.fileService.uploadImage(path,image);
        postDto.setImageName(fileName);

        PostDto updatedPostDto=this.postService.updatePost(postDto,postId);
        return new ResponseEntity<>(updatedPostDto,HttpStatus.OK);
    }

    @GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        InputStream inputStream=this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(inputStream,response.getOutputStream());
    }
}
