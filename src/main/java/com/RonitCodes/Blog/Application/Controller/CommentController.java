package com.RonitCodes.Blog.Application.Controller;

import com.RonitCodes.Blog.Application.Dto.CommentDto;
import com.RonitCodes.Blog.Application.Dto.CommentDtoResponse;
import com.RonitCodes.Blog.Application.Service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/post/{postId}")
    public ResponseEntity<CommentDtoResponse> createComment(@RequestBody CommentDto commentDto,
                                                            @PathVariable Long postId) {
        return ResponseEntity.ok(commentService.saveComment(commentDto, postId));
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("Comment deleted");
    }

}
