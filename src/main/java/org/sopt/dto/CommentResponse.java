package org.sopt.dto;

import org.sopt.domain.Comment;

public record CommentResponse(
        Long userId,
        String content
) {
    public static CommentResponse of(Comment comment){
        return new CommentResponse(comment.getUser().getId(), comment.getContent());
    }
}
