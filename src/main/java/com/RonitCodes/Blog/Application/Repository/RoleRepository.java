package com.RonitCodes.Blog.Application.Repository;

import com.RonitCodes.Blog.Application.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
