package org.sopt.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TagErrorCode implements ErrorCode{

    TAG_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 태그가 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
