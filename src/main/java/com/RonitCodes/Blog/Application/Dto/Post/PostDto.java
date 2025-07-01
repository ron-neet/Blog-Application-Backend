package com.RonitCodes.Blog.Application.Dto.Post;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Data Transfer Object for creating or updating a Post.
 * Ensures that all necessary fields are validated properly.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {

    @NotBlank(message = "Post title is required")
    private String postTitle;

    @NotBlank(message = "Post content is required")
    private String postContent;
}
