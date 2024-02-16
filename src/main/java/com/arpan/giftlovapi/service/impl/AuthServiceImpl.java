package com.arpan.giftlovapi.service.impl;

import com.arpan.giftlovapi.exception.AuthenticationException;
import com.arpan.giftlovapi.model.AuthRequest;
import com.arpan.giftlovapi.model.AuthorizationToken;
import com.arpan.giftlovapi.model.test.SampleRequest;
import com.arpan.giftlovapi.service.AuthService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RestTemplate restTemplate;
    @Value("${endpoint.generateToken:/generateToken}")
    private String generateTokenUrl;

    @Override
    public AuthorizationToken authenticate(@NonNull AuthRequest authRequest) throws AuthenticationException {
//        return generateToken(authRequest);
        return generateTokenTest(authRequest);
    }

    @Override
    public AuthorizationToken refreshToken(@NonNull String username, String refreshToken) throws AuthenticationException {
        return null;
    }

    private AuthorizationToken generateTokenTest(@NonNull final AuthRequest authRequest) {
        String url = "/orders/GL-129172?param1=1&param2=Hi";
        SampleRequest request = new SampleRequest("A");
        try {
            ResponseEntity<SampleRequest> response = restTemplate.postForEntity(url, request, SampleRequest.class);
            log.info("RESPONSE: ", response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private AuthorizationToken generateToken(@NonNull final AuthRequest authRequest) {
        try {
            ResponseEntity<AuthorizationToken> response = restTemplate.postForEntity(generateTokenUrl, authRequest, AuthorizationToken.class);
            log.info("RESPONSE: ", response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
