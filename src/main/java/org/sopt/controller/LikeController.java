package org.sopt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/like")
public class LikeController {

    @PostMapping("/{commentId}")
    public ResponseEntity<Void> toggleCommentLike(
            @PathVariable final Long commentId,
            @RequestHeader final Long userId) {

    }
}
