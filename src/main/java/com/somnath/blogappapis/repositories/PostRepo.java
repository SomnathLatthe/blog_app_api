package com.somnath.blogappapis.repositories;

import com.somnath.blogappapis.entities.Category;
import com.somnath.blogappapis.entities.Post;
import com.somnath.blogappapis.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    //Following are custom finder methods
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);
}
