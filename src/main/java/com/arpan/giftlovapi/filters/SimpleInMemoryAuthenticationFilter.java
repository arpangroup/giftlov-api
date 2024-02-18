package com.arpan.giftlovapi.filters;

import com.arpan.giftlovapi.exception.ErrorCode;
import com.arpan.giftlovapi.exception.ErrorResponse;
import com.arpan.giftlovapi.model.AuthTokenHolder;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
@Order(1)
public class SimpleInMemoryAuthenticationFilter  extends OncePerRequestFilter {
    private final AuthTokenHolder authTokenHolder;
    private final ObjectMapper mapper;
    private final List<String> excludedPaths = List.of("/authenticate", "/registration", "/refreshToken");

    @Value("${jwt.disable-token-expiry-validation:true}")
    boolean disableTokenExpiryValidation;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("Request URL path : {}, Request content type: {}", request.getRequestURI(), request.getContentType());
        if (!isPathExcluded(request) && !disableTokenExpiryValidation) {
            ErrorResponse errorResponse = isValidRequestToken(request);
            if (errorResponse != null) {
                String errorBody = mapper.writeValueAsString(errorResponse);

                response.resetBuffer();
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
                response.getOutputStream().print(errorBody);
                response.flushBuffer(); // marks response as committed -- if we don't do this the request will go through normally!
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private ErrorResponse isValidRequestToken (HttpServletRequest request) {
        ErrorResponse errorResponse = null;
        boolean isValidToken = false;
        try {
            String requestHeader = request.getHeader("Authorization");
            log.info(" Header :  {}", requestHeader);

            if (requestHeader != null) {
                final String jwtToken = requestHeader.trim();
                DecodedJWT decodedJWT = JWT.decode(jwtToken);
                if (isJWTExpired(decodedJWT)) {
                    errorResponse = new ErrorResponse(ErrorCode.TOKEN_EXPIRED);
                } else {
                    isValidToken = true;
                }
            } else {
                errorResponse = new ErrorResponse(ErrorCode.INVALID_HEADER);
            }
        } catch (JWTDecodeException ex) {
            errorResponse = new ErrorResponse(ErrorCode.INVALID_TOKEN);
        } catch (Exception e) {
            errorResponse = new ErrorResponse(e.getMessage(), "");
        }

        return errorResponse;
    }

    boolean isJWTExpired(DecodedJWT decodedJWT) {
        Date expiresAt = decodedJWT.getExpiresAt();
        return expiresAt.before(new Date());
    }

    private boolean isPathExcluded (final HttpServletRequest request) {
        String path = request.getRequestURI();
        return excludedPaths.contains(path);
    }
}
