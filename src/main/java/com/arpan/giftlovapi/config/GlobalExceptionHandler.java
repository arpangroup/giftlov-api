package com.arpan.giftlovapi.config;

import com.arpan.giftlovapi.exception.AuthenticationException;
import com.arpan.giftlovapi.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import java.io.IOException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler{

    @ExceptionHandler({AuthenticationException.class})
    @ResponseBody
    public ResponseEntity<Object> httpClientErrorException(AuthenticationException e) throws AuthenticationException {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getErrorCode());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

//    @ExceptionHandler({HttpClientErrorException.class, HttpStatusCodeException.class, HttpServerErrorException.class})
//    @ResponseBody
//    public ResponseEntity<Object> httpClientErrorException(HttpStatusCodeException e) throws IOException {
//        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.status(e.getRawStatusCode()).header("X-Backend-Status", String.valueOf(e.getRawStatusCode()));
//        if (e.getResponseHeaders().getContentType() != null) {
//            bodyBuilder.contentType(e.getResponseHeaders().getContentType());
//        }
//        return bodyBuilder.body(e.getResponseBodyAsString());
//    }
}
