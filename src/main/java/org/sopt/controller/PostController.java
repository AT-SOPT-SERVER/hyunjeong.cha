package org.sopt.controller;

import jakarta.validation.Valid;
import org.sopt.common.CommonApiResponse;
import org.sopt.common.CommonSuccessCode;
import org.sopt.dto.*;
import org.sopt.service.PostService;
import org.sopt.utils.TextUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contents")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<CommonApiResponse<PostIdResponse>> createPost(
            @Valid @RequestBody final PostRequest request,
            @RequestHeader Long userId) {
        TextUtil.validatePost(request.title(), request.content());
        PostIdResponse response = postService.createPost(request, userId);
        return ResponseEntity.status(CommonSuccessCode.CREATED.getHttpStatus())
                .body(CommonApiResponse.onSuccess(CommonSuccessCode.CREATED, response));

    }

    @GetMapping
    public ResponseEntity<CommonApiResponse<PostAllResponse>> getAllPosts() {
        return ResponseEntity.status(CommonSuccessCode.OK.getHttpStatus())
                .body(CommonApiResponse.onSuccess(CommonSuccessCode.OK,postService.getAllPosts()));
    }

    @GetMapping("/{contentId}")
    public ResponseEntity<CommonApiResponse<PostResponse>> getPostById(@PathVariable final Long contentId) {
        return ResponseEntity.status(CommonSuccessCode.OK.getHttpStatus())
                .body(CommonApiResponse.onSuccess(CommonSuccessCode.OK,postService.getPostById(contentId)));
    }

    @PatchMapping("/{contentId}")
    public ResponseEntity<CommonApiResponse<PostResponse>> updatePostTitle(
            @PathVariable Long contentId,
            @RequestBody PostUpdateRequest request,
            @RequestHeader Long userId) {
        TextUtil.validatePost(request.title(), request.content());
        return ResponseEntity.status(CommonSuccessCode.OK.getHttpStatus())
                .body(CommonApiResponse.onSuccess(CommonSuccessCode.OK,postService.updatePost(contentId, request, userId)));    }

    @DeleteMapping("/{contentId}")
    public ResponseEntity<CommonApiResponse<Void>> deletePostById(
            @PathVariable final Long contentId,
            @RequestHeader Long userId) {
        postService.deletePostById(contentId, userId);
        return ResponseEntity.status(CommonSuccessCode.OK.getHttpStatus())
                .body(CommonApiResponse.onSuccess(CommonSuccessCode.OK));    }

    @GetMapping(value = "/search", params = "keyword")
    public ResponseEntity<CommonApiResponse<PostAllResponse>> searchPostsByKeyword(
            @RequestParam final String keyword
    ) {
        return ResponseEntity.status(CommonSuccessCode.OK.getHttpStatus())
                .body(CommonApiResponse.onSuccess(CommonSuccessCode.OK,postService.searchPostsByKeyword(keyword)));    }

    @GetMapping(value = "/search/tag", params = "tag")
    public ResponseEntity<CommonApiResponse<PostAllResponse>> searchPostsByTag(
            @RequestParam final String tag
    ) {
        return ResponseEntity.status(CommonSuccessCode.OK.getHttpStatus())
                .body(CommonApiResponse.onSuccess(CommonSuccessCode.OK,postService.searchPostsByTag(tag)));    }

}