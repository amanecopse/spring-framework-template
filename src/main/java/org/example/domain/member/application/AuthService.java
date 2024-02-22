package org.example.domain.member.application;

import lombok.RequiredArgsConstructor;
import org.example.domain.member.dao.UserDao;
import org.example.domain.member.dto.LoginRequest;
import org.example.domain.member.dto.SignupRequest;
import org.example.domain.member.dto.UserMapper;
import org.example.global.config.security.TokenProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    public void signup(SignupRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        request.setPassword(encodedPassword);
        userDao.save(userMapper.toEntity(request));
    }

    public String authenticate(LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.createToken(authentication);
    }
}
