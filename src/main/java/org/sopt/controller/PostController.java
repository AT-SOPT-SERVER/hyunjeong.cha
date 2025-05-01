package org.sopt.controller;

import org.sopt.common.CommonApiResponse;
import org.sopt.common.CommonSuccessCode;
import org.sopt.common.PostErrorCode;
import org.sopt.dto.PostAllResponse;
import org.sopt.dto.PostRequest;
import org.sopt.dto.PostIdResponse;
import org.sopt.dto.PostResponse;
import org.sopt.exception.CustomException;
import org.sopt.service.PostService;
import org.sopt.utils.TextUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping("/api/v1/contents")
    public ResponseEntity<CommonApiResponse<PostIdResponse>> createPost(
            @RequestBody final PostRequest request,
            @RequestHeader Long userId) {
        TextUtil.lengthTitleWithEmoji(request.title());
        PostIdResponse response = postService.createPost(request, userId);
        return ResponseEntity.status(CommonSuccessCode.CREATED.getHttpStatus())
                .body(CommonApiResponse.onSuccess(CommonSuccessCode.CREATED, response));

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
    public ResponseEntity<PostResponse> updatePostTitle(
            @PathVariable final Long contentId,
            @RequestBody final PostRequest request,
            @RequestHeader Long userId) {
        TextUtil.lengthTitleWithEmoji(request.title());
        return ResponseEntity.ok(postService.updatePost(contentId, request));
    }

    @DeleteMapping("/api/v1/contents/{contentId}")
    public ResponseEntity<Void> deletePostById(
            @PathVariable final Long contentId,
            @RequestHeader Long userId) {
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