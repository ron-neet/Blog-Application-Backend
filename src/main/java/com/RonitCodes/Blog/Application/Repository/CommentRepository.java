package com.RonitCodes.Blog.Application.Repository;

import com.RonitCodes.Blog.Application.Model.Comment;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
