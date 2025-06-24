package org.sopt.security.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.sopt.utils.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String BEARER_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

            final String token = getJwtFromRequest(request);

            if (StringUtils.hasText(token)){
                Claims claims = jwtUtil.getTokenBody(token);

                Long userId = claims.get("userId", Long.class);
                Authentication auth = new UsernamePasswordAuthenticationToken(userId, null, List.of());

                SecurityContextHolder.getContext().setAuthentication(auth); // 현재 요청에 대한 인증 정보를 Spring Security의 SecurityContext에 저장

            }
            filterChain.doFilter(request, response);

        }

        private String getJwtFromRequest(HttpServletRequest request){
            String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

            if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)){
                return bearerToken.substring(BEARER_PREFIX.length());
            }
            return null;
        }
}
