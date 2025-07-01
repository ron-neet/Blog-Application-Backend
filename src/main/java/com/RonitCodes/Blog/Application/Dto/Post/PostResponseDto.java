package com.RonitCodes.Blog.Application.Dto.Post;

import com.RonitCodes.Blog.Application.Dto.Category.CategoryDto;
import com.RonitCodes.Blog.Application.Dto.Comment.CommentDtoResponse;
import com.RonitCodes.Blog.Application.Dto.User.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponseDto {

    private String postTitle;
    private String postContent;
    private String imageName;
    private String addDate;
    private UserDto user;
    private CategoryDto category;
    private List<CommentDtoResponse> comment = new ArrayList<>();
}