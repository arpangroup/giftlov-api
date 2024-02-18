package com.arpan.giftlovapi.exception;

public class ValidationException extends BaseException{
    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, String errorCode) {
        super(message, errorCode);
    }

    public ValidationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
