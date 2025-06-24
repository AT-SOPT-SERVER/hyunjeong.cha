package org.sopt.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode{
    USER_NOT_REGISTERED(HttpStatus.BAD_REQUEST, "존재하지 않는 유저입니다."),
    EXPIRED_TOKEN_ERROR(HttpStatus.UNAUTHORIZED,  "만료된 토큰입니다."),
    TOKEN_MALFORMED_ERROR(HttpStatus.UNAUTHORIZED, "토큰이 올바르지 않습니다."),
    TOKEN_TYPE_ERROR(HttpStatus.UNAUTHORIZED, "토큰 타입이 일치하지 않거나 비어있습니다."),
    TOKEN_UNSUPPORTED_ERROR(HttpStatus.UNAUTHORIZED, "지원하지않는 토큰입니다."),
    TOKEN_UNKNOWN_ERROR(HttpStatus.UNAUTHORIZED, "알 수 없는 토큰입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "자격 증명이 이루어지지 않았습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
