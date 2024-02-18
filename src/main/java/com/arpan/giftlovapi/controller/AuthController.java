package com.arpan.giftlovapi.controller;

import com.arpan.giftlovapi.exception.AuthenticationException;
import com.arpan.giftlovapi.model.AuthRequest;
import com.arpan.giftlovapi.model.AuthorizationToken;
import com.arpan.giftlovapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthorizationToken> authenticateAndGetToken (@RequestBody AuthRequest authRequest) throws AuthenticationException {
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }
}
