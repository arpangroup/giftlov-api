package com.arpan.giftlovapi.strategy.impl.signature;

import com.arpan.giftlovapi.strategy.RequestParamsExtractionStrategy;
import com.arpan.giftlovapi.strategy.SignatureStrategy;
import org.springframework.http.HttpRequest;

public class EmptySignatureGenerationStrategy implements SignatureStrategy {
    @Override
    public String generateSignature() {
        return "";
    }
}
