package com.somnath.blogappapis.repositories;


import com.somnath.blogappapis.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
}
