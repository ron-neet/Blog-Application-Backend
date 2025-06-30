package com.RonitCodes.Blog.Application.Dto;

import com.RonitCodes.Blog.Application.Model.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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