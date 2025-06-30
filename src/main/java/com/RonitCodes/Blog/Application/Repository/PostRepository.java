package com.RonitCodes.Blog.Application.Repository;

import com.RonitCodes.Blog.Application.Model.Category;
import com.RonitCodes.Blog.Application.Model.Post;
import com.RonitCodes.Blog.Application.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    @Query("select p from Post p where p.postTitle like :postTitle ")
    List<Post> searchByPostTitle(@Param("postTitle") String postTitle);

}
