package org.sopt.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.sopt.security.filter.JwtAuthenticationFilter;
import org.sopt.security.filter.JwtExceptionFilter;
import org.sopt.security.handler.JwtAuthenticationEntryPoint;
import org.sopt.utils.JwtUtil;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public static final String[] AUTH_WHITELIST = {
            "/api/v1/auth/login"
    };

    public static final String[] SWAGGER_WHITELIST = {
            "/swagger",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/api-docs",
            "/api-docs/**",
            "/v3/api-docs/**"
    };

    @Bean
    public JwtExceptionFilter jwtExceptionFilter() {
        return new JwtExceptionFilter(objectMapper); // 생성자에 objectMapper를 넘겨줌
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())  // CSRF 보호 비활성화
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin()) //h2/console 띄우기 위해 필요
                )
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                        .requestMatchers(SWAGGER_WHITELIST).permitAll()
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)

                // Bean으로 등록된 jwtExceptionFilter()를 호출
                .addFilterBefore(jwtExceptionFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
