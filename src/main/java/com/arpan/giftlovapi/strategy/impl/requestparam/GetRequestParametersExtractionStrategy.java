package com.arpan.giftlovapi.strategy.impl.requestparam;

import com.arpan.giftlovapi.strategy.RequestParamsExtractionStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
public class GetRequestParametersExtractionStrategy implements RequestParamsExtractionStrategy {
    private final URI requestUrl;

    @Override
    public String getSortedParams() {
        List<String> paramValues = new ArrayList<>(getUrlParams(requestUrl.toString()).values());
        Collections.sort(paramValues);
        String params = String.join("", paramValues).trim();
        return params.trim();
    }

    private Map<String, String> getUrlParams(String url) {
        int pivot = url.indexOf("?");
        Map<String, String> paramsMap = new HashMap<>();
        try {
            if (pivot > -1) {
                String searchURL = url.substring(url.indexOf("?") + 1);
                String params[] = searchURL.split("&");

                for (String param : params) {
                    String name = param.split("=")[0];
                    String value = param.split("=")[1];
                    paramsMap.put(name, value);
                }
            }
        } catch (Exception e) {
            log.debug("exception at getSortedParams : {}", e.getMessage());
            e.printStackTrace();
        }
        return paramsMap;
    }

}
