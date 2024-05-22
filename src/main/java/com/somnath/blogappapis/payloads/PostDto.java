package com.somnath.blogappapis.payloads;

import com.somnath.blogappapis.entities.Category;
import com.somnath.blogappapis.entities.Comment;
import com.somnath.blogappapis.entities.User;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date createdOn;

    private CategoryDto category;

    private UserDto user;

    private Set<CommentDto> commentSet=new HashSet<>();
}
