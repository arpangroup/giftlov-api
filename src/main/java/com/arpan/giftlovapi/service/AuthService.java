package com.arpan.giftlovapi.service;

import com.arpan.giftlovapi.exception.AuthenticationException;
import com.arpan.giftlovapi.model.AuthRequest;
import com.arpan.giftlovapi.model.AuthorizationToken;
import lombok.NonNull;

public interface AuthService {
    public AuthorizationToken authenticate(@NonNull final AuthRequest authRequest) throws AuthenticationException;
    public AuthorizationToken refreshToken(@NonNull final String username, final String refreshToken) throws AuthenticationException;
}
