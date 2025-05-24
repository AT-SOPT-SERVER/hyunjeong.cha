package org.sopt.dto;

import org.sopt.domain.User;

public record UserSignupResponse(
        Long id
) {
    public static UserSignupResponse from(User user){
        return new UserSignupResponse(user.getId());
    }
}
