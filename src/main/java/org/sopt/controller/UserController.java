package org.sopt.controller;

import org.sopt.common.CommonApiResponse;
import org.sopt.common.CommonSuccessCode;
import org.sopt.common.SuccessCode;
import org.sopt.dto.UserSignupRequest;
import org.sopt.dto.UserSignupResponse;
import org.sopt.service.UserService;
import org.sopt.utils.TextUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<CommonApiResponse<UserSignupResponse>> signup(
            @RequestBody UserSignupRequest request
            ){
        TextUtil.validateName(request.name());
        UserSignupResponse response = userService.signup(request);
        return ResponseEntity.status(CommonSuccessCode.OK.getHttpStatus())
                .body(CommonApiResponse.onSuccess(CommonSuccessCode.OK, response));
    }
}
