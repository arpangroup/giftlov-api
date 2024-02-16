package com.arpan.giftlovapi.strategy.impl.requestparam;

import com.arpan.giftlovapi.strategy.RequestParamsExtractionStrategy;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
public class PostRequestParametersExtractionStrategy implements RequestParamsExtractionStrategy {
    private final byte[] requestBody;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RequestBodyParamsExtractorHelper requestBodyParser;

    @Override
    public String getSortedParams() {
        List<String> paramValues = getRequestBodyParams(requestBody);
        Collections.sort(paramValues);
        String params = String.join("", paramValues).trim();
        return params.trim();
    }

    private List<String> getRequestBodyParams(byte[] requestBody) {
        List<String> paramValues = new ArrayList<>();
        try {
            String jsonRequestBody = new String(requestBody);
            paramValues = requestBodyParser.getAllKeysInJsonUsingJsonNodeFieldNames(jsonRequestBody, objectMapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paramValues;
    }
}
