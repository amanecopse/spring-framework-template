package org.example.global.config.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.example.global.config.security.TokenProvider;
import org.example.global.util.Constant;
import org.example.global.util.Util;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtFilter extends BasicAuthenticationFilter {
    private final TokenProvider tokenProvider;

    public JwtFilter(TokenProvider tokenProvider, AuthenticationManager authenticationManager){
        super(authenticationManager);
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String token = resolveToken(request);
        if (!Util.isNullOrBlank(token)) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        try {
            String bearerToken = request.getHeader(Constant.HEADER_AUTHORIZATION);
            return bearerToken.substring(Constant.BEARER_TOKEN_PREFIX.length() + 1);
        } catch (Exception e) {
            return null;
        }
    }
}
