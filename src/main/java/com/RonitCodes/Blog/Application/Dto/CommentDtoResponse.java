package com.RonitCodes.Blog.Application.Dto;

import com.RonitCodes.Blog.Application.Model.Post;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
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
