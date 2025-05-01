package org.sopt.dto;

import java.util.List;

public record PostAllResponse(
        List<PostListResponse> contentList
) {
}
