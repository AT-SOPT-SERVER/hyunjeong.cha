package org.sopt.common;

import org.springframework.http.HttpStatus;

public interface SuccessCode {

    HttpStatus getHttpStatus();
    String getMessage();
}
