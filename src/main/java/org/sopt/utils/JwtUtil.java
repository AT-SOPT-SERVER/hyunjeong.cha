package org.sopt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sopt.dto.LoginResponse;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil implements InitializingBean {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.access-token-expire-period}")
    @Getter
    private Long accessTokenExpirePeriod;

    private Key key;

    public LoginResponse generateToken(final Long id){
        String accessToken = generateToken(id, accessTokenExpirePeriod);

        return LoginResponse.of(accessToken);
    }

    private String generateToken(final Long id, final Long expirePeriod) {
        Claims claims = Jwts.claims()
                .add("userId", id)
                .build();

        return Jwts.builder()
                .setHeaderParam(Header.JWT_TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirePeriod))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public Claims getTokenBody(final String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }
}
