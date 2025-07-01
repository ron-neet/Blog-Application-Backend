package com.RonitCodes.Blog.Application.Dto.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDtoResponse {

    private Long commentId;
    private String commentContent;

}
