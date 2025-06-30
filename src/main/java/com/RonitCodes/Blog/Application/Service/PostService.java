package com.RonitCodes.Blog.Application.Service;

import com.RonitCodes.Blog.Application.Dto.PostDto;
import com.RonitCodes.Blog.Application.Dto.PostResponseDto;
import com.RonitCodes.Blog.Application.Model.Category;
import com.RonitCodes.Blog.Application.Model.Post;
import com.RonitCodes.Blog.Application.Model.User;
import com.RonitCodes.Blog.Application.Repository.CategoryRepository;
import com.RonitCodes.Blog.Application.Repository.PostRepository;
import com.RonitCodes.Blog.Application.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public PostService(PostRepository postRepository,
                       UserRepository userRepository,
                       CategoryRepository categoryRepository,
                       ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public PostResponseDto createPost(PostDto postDto, Long userId, Long categoryId) {
        // Map DTO to Entity
        Post post = modelMapper.map(postDto, Post.class);

        // Set defaults
        post.setImageName("default.png");
        post.setAddDate(new Date());

        // Fetch and set user and category entities
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        post.setUser(user);
        post.setCategory(category);

        // Save post
        Post savedPost = postRepository.save(post);

        // Map saved entity to response DTO
        return modelMapper.map(savedPost, PostResponseDto.class);
    }


    public List<PostResponseDto> getAllPosts(int pageNumber, int pageSize,  String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());

        Page<Post> pagePosts = postRepository.findAll(pageable);
        List<Post> posts = pagePosts.getContent();

        List<PostResponseDto> postResponseDtoList = posts.stream()
                .map(post -> modelMapper.map(post, PostResponseDto.class))
                .collect(Collectors.toList());

        return postResponseDtoList;
    }

    public PostResponseDto getPostById(Long id) {
        Post findPost = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        return modelMapper.map(findPost, PostResponseDto.class);
    }

    public List<PostResponseDto> getPostByCategory(long categoryId){

        Category cat = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        List<Post> findPostByCategory = postRepository.findByCategory(cat);

        List<PostResponseDto> response = findPostByCategory
                .stream()
                .map(post -> modelMapper.map(post, PostResponseDto.class))
                .collect(Collectors.toList());

        return response;
    }

    public List<PostResponseDto> getPostByUserId(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Post> findPost = postRepository.findByUser(user);

        List<PostResponseDto> response = findPost
                .stream()
                .map(post -> modelMapper.map(post, PostResponseDto.class))
                .collect(Collectors.toList());

        return response;
    }

    public void deletePostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        postRepository.delete(post);
    }

    public PostResponseDto updatePost(Long PostId, PostDto postDto) {

        Post post = postRepository.findById(PostId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        post.setPostContent(postDto.getPostContent());
        post.setPostTitle(postDto.getPostTitle());

        return modelMapper.map(postRepository.save(post), PostResponseDto.class);
    }

    public List<PostResponseDto> searchByPostTitle(String postTitle) {
        List<Post> posts = postRepository.searchByPostTitle("%"+postTitle+"%");
        List<PostResponseDto> searchedPosts = posts
                .stream()
                .map(post -> modelMapper.map(post, PostResponseDto.class))
                .collect(Collectors.toList());

        return searchedPosts;
    }

    // Upload the image and set to the post
    public PostResponseDto uploadImage(Long postId, String fileName){

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        post.setImageName(fileName);

        Post updatedPost =  postRepository.save(post);

        return modelMapper.map(updatedPost, PostResponseDto.class);
    }


}
