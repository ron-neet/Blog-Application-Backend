package com.RonitCodes.Blog.Application.Service;

import com.RonitCodes.Blog.Application.Dto.CommentDto;
import com.RonitCodes.Blog.Application.Dto.CommentDtoResponse;
import com.RonitCodes.Blog.Application.Model.Comment;
import com.RonitCodes.Blog.Application.Model.Post;
import com.RonitCodes.Blog.Application.Repository.CommentRepository;
import com.RonitCodes.Blog.Application.Repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public CommentService(CommentRepository commentRepository,
                          PostRepository postRepository,
                          ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    public CommentDtoResponse saveComment(CommentDto commentDto, Long postId) {

        Post findPost = postRepository.findById(postId)
                .orElseThrow(()-> new EntityNotFoundException("Post not found"));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(findPost);

        Comment saveComment =  this.commentRepository.save(comment);

        return modelMapper.map(saveComment, CommentDtoResponse.class);
    }

    public void deleteComment(Long commentId){

        Comment findComment = commentRepository.findById(commentId)
                .orElseThrow(()-> new EntityNotFoundException("Comment not found"));

        commentRepository.delete(findComment);
    }
}
