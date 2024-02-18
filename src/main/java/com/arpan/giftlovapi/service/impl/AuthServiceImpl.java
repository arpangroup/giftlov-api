package com.arpan.giftlovapi.service.impl;

import com.arpan.giftlovapi.exception.AuthenticationException;
import com.arpan.giftlovapi.exception.ErrorCode;
import com.arpan.giftlovapi.model.AuthRequest;
import com.arpan.giftlovapi.model.AuthTokenHolder;
import com.arpan.giftlovapi.model.AuthorizationToken;
import com.arpan.giftlovapi.service.AuthService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RestTemplate restTemplate;
    private final AuthTokenHolder authTokenHolder;

    @Value("${endpoint.generateToken:/generateToken}")
    private String generateTokenUrl;

    @Override
    public AuthorizationToken authenticate(@NonNull AuthRequest authRequest) throws AuthenticationException {
        AuthorizationToken authToken = generateToken(authRequest);
        authTokenHolder.setAuthorizationToken(authToken);
        return authToken;
    }

    @Override
    public AuthorizationToken refreshToken(@NonNull String username, String refreshToken) throws AuthenticationException {
        return null;
    }

    private AuthorizationToken generateToken(@NonNull final AuthRequest authRequest) throws AuthenticationException {
        try {
            ResponseEntity<AuthorizationToken> response = restTemplate.postForEntity(generateTokenUrl, authRequest, AuthorizationToken.class);
            return response.getBody();
        } catch (HttpStatusCodeException e) {
            e.printStackTrace();
            if (e.getStatusCode().value() == 401) { //Unauthorized
                //String responseString = e.getResponseBodyAsString();
                throw new AuthenticationException(ErrorCode.UNAUTHORIZED);
            }
            throw new AuthenticationException(e.getMessage());
        }
    }
}
