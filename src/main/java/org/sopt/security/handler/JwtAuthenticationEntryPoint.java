package org.sopt.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.common.AuthErrorCode;
import org.sopt.common.CommonApiResponse;
import org.sopt.common.ErrorCode;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    public void commence(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException authException) throws IOException {
        ErrorCode errorCode = (ErrorCode) request.getAttribute("exception");
        if (errorCode == null) {
            log.info("auth");
            errorCode = AuthErrorCode.UNAUTHORIZED;
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(errorCode.getHttpStatus().value());

        CommonApiResponse<?> baseResponse = CommonApiResponse.onFailure(errorCode);

        // JSON 직렬화
        response.getWriter().write(
                objectMapper.writeValueAsString(baseResponse)
        );
    }
}
