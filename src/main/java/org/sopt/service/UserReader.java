package org.sopt.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.User;
import org.sopt.exception.CustomException;
import org.sopt.repository.UserRepository;
import org.springframework.stereotype.Component;

import static org.sopt.common.UserErrorCode.USER_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class UserReader {

    private final UserRepository userRepository;

    public User getById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));
    }
}
