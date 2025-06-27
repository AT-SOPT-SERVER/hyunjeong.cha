package org.sopt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record PostRequest(
        @NotBlank(message = "제목은 비어있을 수 없습니다.")
        String title,
        @NotBlank(message = "내용은 비어있을 수 없습니다.")
        String content,
        @NotEmpty(message = "태그는 비어있을 수 없습니다.")
        List<String> postType
) {

}
