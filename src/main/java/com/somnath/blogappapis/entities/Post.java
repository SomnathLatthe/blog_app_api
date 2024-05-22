package com.somnath.blogappapis.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
   @Column(name = "post_title",length = 100,nullable = false)
   private String title;
   @Column(length = 1000)
   private String content;
   private String imageName;
   private Date createdOn;
   @ManyToOne
   private Category category;
   @ManyToOne
   private User user;

   @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
   private Set<Comment> commentSet=new HashSet<>();
}
