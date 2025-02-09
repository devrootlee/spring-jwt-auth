package com.spring.jwt.auth.api.common.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtProvider jwtProvider;

    public JwtAuthenticationFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        //허용된 URI 목록
        String[] permitUris = {"/h2", "/h2-console", "/user/signUp", "/user/login","/transfer/quote"};

        if (isPermitUri(requestURI,permitUris)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String jwt = checkJwt(request);

            if (jwt == null || !jwtProvider.validateJwt(jwt)) {
                throw new SecurityException();
            }
            String memberId = jwtProvider.getUserId(jwt);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(memberId, null, List.of());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } catch (SecurityException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"resultCode\": \"401\", \"resultMsg\": \"" + e.getMessage() + "\"}");
        }

    }

    //jwt 형식이 맞는지 확인
    private String checkJwt(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    //URI가 허용된 목록에 있는지 확인
    private boolean isPermitUri(String requestURI, String[] permitUris) {
        for (String uri : permitUris) {
            if (requestURI.startsWith(uri)) {
                return true;
            }
        }
        return false;
    }
}
