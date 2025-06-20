package org.sopt.dto;

import org.sopt.domain.Post;

import java.util.List;

public record PostResponse(
        String title,
        Long contentId,
        String writer,
        String content,
        List<CommentResponse> commentResponses
) {
    public static PostResponse from(Post post, List<CommentResponse> commentResponses){
        return new PostResponse(post.getTitle(), post.getId(), post.getUser().getName(), post.getContent(), commentResponses);
    }
}
