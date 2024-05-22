package com.somnath.blogappapis.services.Impl;

import com.somnath.blogappapis.entities.Comment;
import com.somnath.blogappapis.entities.Post;
import com.somnath.blogappapis.exceptions.ResourceNotFoundException;
import com.somnath.blogappapis.payloads.CommentDto;
import com.somnath.blogappapis.repositories.CommentRepo;
import com.somnath.blogappapis.repositories.PostRepo;
import com.somnath.blogappapis.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
        Comment comment=this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment=this.commentRepo.save(comment);
        return this.modelMapper.map(comment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","comment id",commentId));
        this.commentRepo.delete(comment);
    }
}
