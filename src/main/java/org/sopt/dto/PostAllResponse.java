package org.sopt.dto;

import java.util.List;

public record PostAllResponse(
        List<PostListResponse> contentList
) {
    public static PostAllResponse from(List<PostListResponse> contentList){
        return new PostAllResponse(contentList);
    }
}
