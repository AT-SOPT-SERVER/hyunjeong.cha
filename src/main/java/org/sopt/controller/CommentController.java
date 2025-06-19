package org.sopt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.common.CommonApiResponse;
import org.sopt.common.CommonSuccessCode;
import org.sopt.dto.CommentIdResponse;
import org.sopt.dto.CommentRequest;
import org.sopt.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity<CommonApiResponse<CommentIdResponse>> createComment(
            @Valid @RequestBody CommentRequest request,
            @PathVariable final Long postId,
            @RequestHeader final Long userId
            ){
        CommentIdResponse response = commentService.createComment(request, userId, postId);
        return ResponseEntity.status(CommonSuccessCode.OK.getHttpStatus())
                .body(CommonApiResponse.onSuccess(CommonSuccessCode.OK,response));
    }

}
