package com.arpan.giftlovapi.exception;

import lombok.Getter;
import lombok.NonNull;

import java.io.IOException;

@Getter
public class DownstreamApiException extends IOException {
    private ErrorResponse errorResponse;
    public DownstreamApiException(String message, String errorCode) {
        super(message);
        this.errorResponse = new ErrorResponse(message, errorCode);
    }

    public DownstreamApiException(@NonNull ErrorResponse errorResponse) {
        super(errorResponse.getMessage());
        this.errorResponse = errorResponse;
    }
}
