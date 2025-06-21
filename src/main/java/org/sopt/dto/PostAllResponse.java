package org.sopt.dto;

import org.sopt.domain.Post;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public record PostAllResponse(
        int totalElements,
        int totalPages,
        List<PostListResponse> contentList
) {
    public static PostAllResponse from(Page<Post> postPage){
        return new PostAllResponse(
                (int) postPage.getTotalElements(),
                postPage.getTotalPages(),
                postPage.stream().map(PostListResponse::from)
                        .collect(Collectors.toList())
        );
    }

}
