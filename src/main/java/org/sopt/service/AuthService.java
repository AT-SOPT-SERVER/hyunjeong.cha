package org.sopt.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.User;
import org.sopt.dto.LoginRequest;
import org.sopt.dto.LoginResponse;
import org.sopt.utils.JwtUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserReader userReader;
    private final JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request){
        User findUser = userReader.findByIdAndName(request.userId(), request.name());
            return jwtUtil.generateToken(findUser.getId());
    }
}
