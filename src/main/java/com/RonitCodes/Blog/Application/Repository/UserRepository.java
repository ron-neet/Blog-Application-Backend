package com.RonitCodes.Blog.Application.Repository;

import com.RonitCodes.Blog.Application.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}