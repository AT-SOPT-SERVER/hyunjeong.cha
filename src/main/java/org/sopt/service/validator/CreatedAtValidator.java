package org.sopt.service.validator;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.sopt.exception.CommonException.CREATE_DURATION;

public class CreatedAtValidator {

    public void createdAtValidate(LocalDateTime oldCreatedAt){

        Duration duration = Duration.between(oldCreatedAt, LocalDateTime.now());

        if (duration.getSeconds() <= 180)
            throw new IllegalStateException(CREATE_DURATION.getMessage());
    }
}
