package com.RonitCodes.Blog.Application.Repository;

import com.RonitCodes.Blog.Application.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}