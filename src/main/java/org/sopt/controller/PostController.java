package org.sopt.controller;

import org.sopt.dto.PostAllResponse;
import org.sopt.dto.PostRequest;
import org.sopt.dto.PostIdResponse;
import org.sopt.dto.PostResponse;
import org.sopt.service.PostService;
import org.sopt.utils.TextUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.sopt.exception.CommonException.TOO_LONG_TITLE;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping("/api/v1/contents")
    public ResponseEntity<PostIdResponse> createPost(@RequestBody final PostRequest request) {
        if (TextUtil.lengthTitleWithEmoji(request.title())> 30) {
            throw new IllegalArgumentException(TOO_LONG_TITLE.getMessage());
        }
        PostIdResponse response = postService.createPost(request);
        return ResponseEntity.created(URI.create("/api/v1/contents")).body(response);

    }

    @GetMapping("/api/v1/contents")
    public ResponseEntity<PostAllResponse> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/api/v1/contents/{contentId}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable final Long contentId) {
        return ResponseEntity.ok(postService.getPostById(contentId));
    }

    @PatchMapping("/api/v1/contents/{contentId}")
    public ResponseEntity<PostResponse> updatePostTitle(@PathVariable final Long contentId,
                                   @RequestBody final PostRequest request) {
        if (TextUtil.lengthTitleWithEmoji(request.title())> 30) {
            throw new IllegalArgumentException(TOO_LONG_TITLE.getMessage());
        }
        return ResponseEntity.ok(postService.updatePost(contentId, request));
    }

    @DeleteMapping("/api/v1/contents/{contentId}")
    public ResponseEntity<Void> deletePostById(@PathVariable final Long contentId) {
        postService.deletePostById(contentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/api/v1/contents", params = "keyword")
    public ResponseEntity<PostAllResponse> searchPostsByKeyword(
            @RequestParam final String keyword
    ) {
        return ResponseEntity.ok(postService.searchPostsByKeyword(keyword));
    }
}