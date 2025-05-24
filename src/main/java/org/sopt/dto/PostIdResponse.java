package org.sopt.dto;

import org.sopt.domain.Post;

public record PostIdResponse(
        Long contentId
) {
    public static PostIdResponse from(Post post){
        return new PostIdResponse(post.getId());
    }
}
