package com.somnath.blogappapis.services;

import com.somnath.blogappapis.entities.Category;
import com.somnath.blogappapis.entities.Post;
import com.somnath.blogappapis.entities.User;
import com.somnath.blogappapis.payloads.PostDto;
import com.somnath.blogappapis.payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    PostDto updatePost(PostDto postDto,Integer postId);

    void deletePost(Integer postId);

    PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    PostDto getPostById(Integer postId);

    List<PostDto> getPostsByUser(Integer userId);

    List<PostDto> getPostsByCategory(Integer categoryId);

    List<PostDto> searchPosts(String keyword);

}
