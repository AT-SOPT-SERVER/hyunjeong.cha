package org.sopt.dto;

import java.util.List;

public record PostSearchResponse(
        List<PostListResponse> contentList

) {
    public static PostSearchResponse from(List<PostListResponse> postList){
        return new PostSearchResponse(postList);
    }
}
