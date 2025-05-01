package org.sopt.common;

import org.springframework.http.HttpStatus;

public enum PostErrorCode implements ErrorCode {

    EMPTY_TITLE(HttpStatus.BAD_REQUEST,"제목이 비어있을 수 없습니다."),
    TOO_LONG_TITLE(HttpStatus.BAD_REQUEST, "제목은 30자 미만이어야 합니다."),
    EMPTY_POST(HttpStatus.NOT_FOUND, "해당 게시글이 존재하지 않습니다."),
    DUPLICATE_TITLE(HttpStatus.BAD_REQUEST, "같은 제목의 게시글은 작성할 수 없습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    PostErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public String getMessage() {
        return this.message;
    }
}
