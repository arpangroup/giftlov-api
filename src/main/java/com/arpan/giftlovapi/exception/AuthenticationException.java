package com.arpan.giftlovapi.exception;

public class AuthenticationException extends BaseException{
    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, String errorCode) {
        super(message, errorCode);
    }

    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
