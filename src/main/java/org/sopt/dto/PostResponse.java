package org.sopt.dto;

import org.sopt.domain.Post;

public record PostResponse(
        String title,
        Long contentId,
        String writer,
        String content
) {
    public static PostResponse from(Post post){
        return new PostResponse(post.getTitle(), post.getId(), post.getUser().getName(), post.getContent());
    }
}
