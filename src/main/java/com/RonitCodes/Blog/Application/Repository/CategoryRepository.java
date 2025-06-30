package com.RonitCodes.Blog.Application.Repository;

import com.RonitCodes.Blog.Application.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
