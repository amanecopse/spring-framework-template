package org.example.domain.member.exception;

public class UserNotAllowedException extends RuntimeException{
    public UserNotAllowedException() {
        this("권한이 없습니다");
    }
    public UserNotAllowedException(String message) {
        super(message);
    }
}
