package com.arpan.giftlovapi.exception;

import lombok.Getter;

@Getter
public abstract class BaseException extends Exception{
    private String errorCode;
    public BaseException(String message) {
        super(message);
    }
    public BaseException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    public BaseException(ErrorCode errorCode) {
        super(errorCode.errorMessage());
        this.errorCode = String.valueOf(errorCode.errorCode());
    }
}
