package org.sopt.exception;

import org.sopt.common.CommonApiResponse;
import org.sopt.common.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CommonApiResponse<?>> handleCustomException(CustomException ex) {
        ErrorCode errorCode = ex.getErrorCode();

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(CommonApiResponse.onFailure(errorCode));
    }
}
