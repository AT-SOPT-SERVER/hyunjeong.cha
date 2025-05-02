package org.sopt.service;

import org.sopt.domain.User;
import org.sopt.dto.UserSignupRequest;
import org.sopt.dto.UserSignupResponse;
import org.sopt.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserSignupResponse signup(UserSignupRequest request){
        User user = new User(request.name());
        return UserSignupResponse.from(userRepository.save(user));
    }
}
