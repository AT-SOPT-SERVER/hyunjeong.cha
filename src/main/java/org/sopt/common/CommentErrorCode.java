package org.sopt.common;

import org.springframework.http.HttpStatus;

public enum CommentErrorCode implements ErrorCode{

    TOO_LONG_CONTENT(HttpStatus.BAD_REQUEST, "댓글은 300자 이하이어야 합니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 게시글이 존재하지 않습니다."),

    private final HttpStatus httpStatus;
    private final String message;

    CommentErrorCode(HttpStatus httpStatus, String message) {
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
