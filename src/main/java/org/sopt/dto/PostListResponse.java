package org.sopt.dto;

import org.sopt.domain.Post;

public record PostListResponse(
        String title,
        Long contentId,
        String writer
) {
    public static PostListResponse from(Post post){
        return new PostListResponse(post.getTitle(), post.getId(), post.getUser().getName());
    }
}
