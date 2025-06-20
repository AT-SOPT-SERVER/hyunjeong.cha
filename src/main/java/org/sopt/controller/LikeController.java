package org.sopt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.common.CommonApiResponse;
import org.sopt.common.CommonSuccessCode;
import org.sopt.dto.ToggleLikeRequest;
import org.sopt.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<CommonApiResponse<Void>> toggleLike(
            @Valid @RequestBody final ToggleLikeRequest request,
            @RequestHeader final Long userId) {
        likeService.toggleLike(userId, request);

        return ResponseEntity.status(CommonSuccessCode.OK.getHttpStatus())
                .body(CommonApiResponse.onSuccess(CommonSuccessCode.OK));
    }
}
