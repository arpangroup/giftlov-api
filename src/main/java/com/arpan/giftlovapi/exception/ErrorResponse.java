package com.arpan.giftlovapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class ErrorResponse implements Serializable {
    private String message;
    private String errorCode;

    public ErrorResponse(ErrorCode errorCode) {
        this.message = errorCode.errorMessage();
        this.errorCode = String.valueOf(errorCode.errorCode());
    }
}
