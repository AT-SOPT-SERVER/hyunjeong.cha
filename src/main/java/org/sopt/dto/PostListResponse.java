package org.sopt.dto;

public record PostListResponse(
        String title,
        Long contentId,
        String writer
) {
}
