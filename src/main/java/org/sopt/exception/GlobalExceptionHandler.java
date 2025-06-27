package org.sopt.exception;

import jakarta.validation.ConstraintViolationException;
import org.sopt.common.CommonApiResponse;
import org.sopt.common.CommonErrorCode;
import org.sopt.common.ErrorCode;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonApiResponse<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        ErrorCode errorCode = CommonErrorCode.INVALID_INPUT_VALUE;

        String message = e.getBindingResult().getAllErrors().stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse(errorCode.getMessage());

        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(CommonApiResponse.onFailure(errorCode, message));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<CommonApiResponse<Void>> handleMissingServletRequestParameterException(MissingServletRequestParameterException e){
        ErrorCode errorCode = CommonErrorCode.MISSING_PARAMETER;
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(CommonApiResponse.onFailure(errorCode));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonApiResponse<Void>> handleUnexpectedException(Exception e) {
        ErrorCode errorCode = CommonErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(CommonApiResponse.onFailure(errorCode));
    }
}
