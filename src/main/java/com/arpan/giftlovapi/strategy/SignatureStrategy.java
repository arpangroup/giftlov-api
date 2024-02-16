package com.arpan.giftlovapi.strategy;

import org.springframework.http.HttpRequest;

public interface SignatureStrategy {
    String generateSignature();
}
