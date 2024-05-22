package com.somnath.blogappapis.services;

import com.somnath.blogappapis.payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Integer postId);

    void deleteComment(Integer commentId);
}
