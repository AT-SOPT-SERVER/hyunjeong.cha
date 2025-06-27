package org.sopt.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CommentRequest(
        @NotEmpty(message = "댓글은 비어있을 수 없습니다.")
        @Size(message = "댓글은 300자 이내로 작성할 수 있습니다.", max = 300)
        String content
) {
}
