package org.sopt.dto;

import jakarta.validation.constraints.NotNull;
import org.sopt.domain.enums.LikeType;

public record ToggleLikeRequest(
        @NotNull(message = "댓글 또는 게시글 ID는 null일 수 없습니다.")
        Long id,
        @NotNull(message = "좋아요 타입은 null일 수 없습니다.")
        LikeType likeType
) {
}
