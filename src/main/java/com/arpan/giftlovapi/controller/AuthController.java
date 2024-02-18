package com.arpan.giftlovapi.controller;

import com.arpan.giftlovapi.exception.AuthenticationException;
import com.arpan.giftlovapi.model.AuthRequest;
import com.arpan.giftlovapi.model.AuthorizationToken;
import com.arpan.giftlovapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthorizationToken> authenticateAndGetToken (@RequestBody AuthRequest authRequest) throws AuthenticationException {
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }
}
