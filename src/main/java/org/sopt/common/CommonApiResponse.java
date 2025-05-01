package org.sopt.common;

import com.fasterxml.jackson.annotation.JsonInclude;

public class CommonApiResponse<T>{
    private final int code;
    private final String message;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private T result;

    public CommonApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonApiResponse(int code, String message, T result) {
        this.result = result;
        this.message = message;
        this.code = code;
    }

    //성공한 경우
    public static <T> CommonApiResponse<T> onSuccess(SuccessCode successCode){
        return new CommonApiResponse<>(successCode.getHttpStatus().value(), successCode.getMessage());
    }

    public static <T> CommonApiResponse<T> onSuccess(SuccessCode successCode, T data){
        return new CommonApiResponse<>(successCode.getHttpStatus().value(), successCode.getMessage(), data);
    }

    //실패한 경우
    public static <T> CommonApiResponse<T> onFailure(ErrorCode errorCode){
        return new CommonApiResponse<>(errorCode.getHttpStatus().value(), errorCode.getMessage());
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getResult() {
        return result;
    }
}
