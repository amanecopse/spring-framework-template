package org.example.domain.member.exception;

public class LoginRequiredException extends RuntimeException{
    public LoginRequiredException() {
        this("로그인이 필요합니다");
    }
    public LoginRequiredException(String message) {
        super(message);
    }
}
