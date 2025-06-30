package com.RonitCodes.Blog.Application.Controller;

import com.RonitCodes.Blog.Application.Dto.PostDto;
import com.RonitCodes.Blog.Application.Dto.PostResponseDto;
import com.RonitCodes.Blog.Application.Service.FileService;
import com.RonitCodes.Blog.Application.Service.PostService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PostController {

    private final PostService postService;
    private final FileService fileService;

    public PostController(PostService postService,  FileService fileService) {
        this.postService = postService;
        this.fileService = fileService;
    }

    @Value("${project.image}")
    private String path;


    @PostMapping("/user/{userId}/category/{categoryId}/post")
    public ResponseEntity<PostResponseDto> addPost(
            @Valid @RequestBody PostDto postDto,
            @PathVariable Long userId,
            @PathVariable Long categoryId) {

        PostResponseDto createdPost = postService.createPost(postDto, userId, categoryId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @GetMapping("/getAllPost")
    public ResponseEntity<List<PostResponseDto>> getAllPost(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "4", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy
            ) {
        return ResponseEntity.ok(postService.getAllPosts(pageNumber, pageSize, sortBy));
    }

    @GetMapping("/getPostById/{postId}/post")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @GetMapping("/getByCategory/{categoryId}/post")
    public ResponseEntity<List<PostResponseDto>> getByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(postService.getPostByCategory(categoryId));
    }

    @GetMapping("/user/{userId}/post")
    public ResponseEntity<List<PostResponseDto>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(postService.getPostByUserId(userId));
    }

    @DeleteMapping("/post/{postId}")
    public String deletePost(@PathVariable Long postId) {
        postService.deletePostById(postId);
        return "Deleted sucessfully";
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long postId,
                                                      @Valid @RequestBody PostDto postDto) {
        return ResponseEntity.ok(postService.updatePost(postId, postDto));
    }

    @GetMapping("/post/search/{postTitle}")
    public ResponseEntity<List<PostResponseDto>> searchPost(@PathVariable String postTitle) {
        return ResponseEntity.ok(postService.searchByPostTitle(postTitle));
    }

    @PostMapping("/postImage/upload/{postId}")
    public ResponseEntity<String> uploadFile(@RequestParam("image")MultipartFile file,
                                             @PathVariable Long postId) throws IOException {

        String fileName = fileService.uploadFile(path, file);

        postService.uploadImage(postId, fileName);

        return ResponseEntity.ok("Hehehe uploaded successfully");
    }

    @GetMapping("/post/image/{imageName}")
    public void getPostImage(
            @PathVariable(value = "imageName") String imageName,
            HttpServletResponse response) throws IOException {
        InputStream resource = fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
}
