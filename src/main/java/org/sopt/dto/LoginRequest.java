package org.sopt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(
        @NotEmpty(message = "id는 비어있을 수 없습니다.")
        Long userId,
        @NotBlank(message = "이름은 비어있을 수 없습니다.")
        String name
) {
}
