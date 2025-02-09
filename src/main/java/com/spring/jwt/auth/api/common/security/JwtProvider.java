package com.spring.jwt.auth.api.common.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    //시크릿 키

    @Value("${jwt.secretkey}")
    private String secretKey;

    //jwt 만료시간
    @Value("${jwt.expiration}")
    private long jwtExpiration;

    /**
     * jwt 생성
     * @param userId
     * @return
     * @throws Exception
     */
    public String generateJwt(String userId) throws Exception {
        Date jwtExpirationTime = new Date(System.currentTimeMillis() + jwtExpiration);

        return Jwts.builder()
                .setSubject(userId) //사용자 아이디
                .setIssuedAt(new Date()) //jwt 발행 시간
                .setExpiration(jwtExpirationTime) //jwt 만료 시간
                .signWith(SignatureAlgorithm.HS256, secretKey) //HS256 방식으로 서명
                .compact();
    }

    public boolean validateJwt(String jwt) {
        try {
            jwt = jwt.replace("Bearer ", "");
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(jwt);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getUserId(String jwt) {
        jwt = jwt.replace("Bearer ", "");

        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }
}
