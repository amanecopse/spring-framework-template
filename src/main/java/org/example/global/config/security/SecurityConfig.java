package org.example.global.config.security;

import lombok.RequiredArgsConstructor;
import org.example.global.config.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity // 스프링 필터 체인에 스프링 시큐리티 필터를 등록
@EnableMethodSecurity // 메소드 단위 권한 설정 활성화
@RequiredArgsConstructor
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final AuthenticationEntryPoint entryPoint;
    private final JwtAccessDeniedHandler accessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity builder, AuthenticationManager authenticationManager)
            throws Exception {
        AntPathRequestMatcher[] apiWhitelist = new AntPathRequestMatcher[]{
                new AntPathRequestMatcher("/swagger-ui/**"),
                new AntPathRequestMatcher("/swagger-resources/**"),
                new AntPathRequestMatcher("/**/api-docs/**"),
                new AntPathRequestMatcher("/"),
                new AntPathRequestMatcher("/member/authenticate"),
                new AntPathRequestMatcher("/member/signup")
        };
        builder.authorizeHttpRequests(authorizeHttpRequests ->
                authorizeHttpRequests
                        .requestMatchers(apiWhitelist).permitAll()
                        .anyRequest().authenticated()
        );
        builder.csrf(AbstractHttpConfigurer::disable);
        builder.formLogin(AbstractHttpConfigurer::disable);
        builder.sessionManagement(sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        builder.addFilterBefore(new JwtFilter(tokenProvider, authenticationManager), BasicAuthenticationFilter.class);
        builder.exceptionHandling(exceptionHandling ->
                exceptionHandling
                        .authenticationEntryPoint(entryPoint)
                        .accessDeniedHandler(accessDeniedHandler));
        return builder.build();
    }
}
