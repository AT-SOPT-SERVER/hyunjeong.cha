package org.sopt.controller;

import org.sopt.domain.Post;
import org.sopt.dto.PostRequest;
import org.sopt.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping("/api/v1/contents")
    public void createPost(@RequestBody final PostRequest request) {
        try {
            postService.createPost(request);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/api/v1/contents")
    public ResponseEntity<?> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/api/v1/contents/{contentId}")
    public Post getPostById(@PathVariable final Long contentId) {
        return postService.getPostById(contentId);
    }

    @PatchMapping("/api/v1/contents/{contentId}")
    public Boolean updatePostTitle(@PathVariable final Long contentId,
                                   @RequestBody final PostRequest request) {
        try {
            return postService.updatePost(contentId, request);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @DeleteMapping("/api/v1/contents/{contentId}")
    public boolean deletePostById(@PathVariable final Long contentId) {
        return postService.deletePostById(contentId);
    }

    @GetMapping("/api/v1/contents/{keyword}")
    public List<Post> searchPostsByKeyword(
            @PathVariable final String keyword
    ) {
        return postService.searchPostsByKeyword(keyword);
    }

    public void loadFile() {
        try {
            postService.loadFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}