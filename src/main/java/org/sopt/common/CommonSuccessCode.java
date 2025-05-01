package org.sopt.common;

import org.springframework.http.HttpStatus;

public enum CommonSuccessCode implements SuccessCode {

    OK(HttpStatus.OK, "요청이 성공했습니다."),
    NO_CONTENT(HttpStatus.NO_CONTENT, "요청이 성공했습니다."),
    CREATED(HttpStatus.CREATED, "리소스가 생성되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    CommonSuccessCode(HttpStatus httpStatus, String message) {
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
