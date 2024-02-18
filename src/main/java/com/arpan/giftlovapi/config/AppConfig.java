package com.arpan.giftlovapi.config;

import com.arpan.giftlovapi.interceptors.LoggingRequestInterceptor;
import com.arpan.giftlovapi.interceptors.RestTemplateTokenAuthorizationInterceptor;
import com.arpan.giftlovapi.strategy.impl.requestparam.RequestBodyParamsExtractorHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.function.Supplier;

@Configuration
public class AppConfig {
    @Value("${endpoint.base:https://staging.giftlov.com/api/Base}")
    private String baseUri;
    @Value("${secretKey:coding_challenge_1}")
    String secretKey;

    String accessToken = "xxxxxxxapple";


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) throws Exception {
        RequestBodyParamsExtractorHelper paramsExtractorHelper = new RequestBodyParamsExtractorHelper();
        RestTemplateTokenAuthorizationInterceptor authorizationInterceptor = new RestTemplateTokenAuthorizationInterceptor(
                baseUri,
                paramsExtractorHelper,
                accessToken,
                secretKey
        );
         return builder
                .rootUri(baseUri)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                 //.errorHandler(new RestTemplateResponseErrorHandler())
                .interceptors(
                        authorizationInterceptor
                        //new LoggingRequestInterceptor()
                )
                .build();
    }

}