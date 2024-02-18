package com.arpan.giftlovapi.interceptors;

import com.arpan.giftlovapi.strategy.SignatureStrategy;
import com.arpan.giftlovapi.strategy.impl.requestparam.GetRequestParametersExtractionStrategy;
import com.arpan.giftlovapi.strategy.impl.requestparam.PostRequestParametersExtractionStrategy;
import com.arpan.giftlovapi.strategy.RequestParamsExtractionStrategy;
import com.arpan.giftlovapi.strategy.impl.requestparam.RequestBodyParamsExtractorHelper;
import com.arpan.giftlovapi.strategy.impl.signature.RequestParameterSignatureStrategy;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@Slf4j
public class RestTemplateTokenAuthorizationInterceptor implements ClientHttpRequestInterceptor {
    private final String baseUri;
    private final RequestBodyParamsExtractorHelper requestBodyParamsExtractorHelper;
    private final String accessToken;
    private final String secretKey;

    private static final String HEADER_DATE = "X-GIFTLOV-DATE";
    private static final String AUTHORIZATION = "Authorization";
    private static final String SIGNATURE = "signature";
        private static final List<String> IGNORE_PATH = List.of("/generateToken");

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        String currentDateTime = getCurrentDate();
        addCommonHeader(request, currentDateTime);
        if (isProtectedRoute(request.getURI())) {
            addSignatureHeader(request, body, currentDateTime);
        }
        return execution.execute(request, body);
    }

    private void addSignatureHeader(HttpRequest request, byte[] body, String currentDateTime) {
        SignatureStrategy signatureStrategy = new RequestParameterSignatureStrategy(
                baseUri,
                request,
                body,
                requestBodyParamsExtractorHelper,
                currentDateTime,
                accessToken,
                secretKey
        );
        String signature = signatureStrategy.generateSignature();
        request.getHeaders().add(SIGNATURE, signature);
    }

    private void addCommonHeader(HttpRequest request, String currentDate) {
        request.getHeaders().add(HEADER_DATE, currentDate);
    }

    private boolean isProtectedRoute(URI uri) {
        String url = String.valueOf(uri);
        return IGNORE_PATH.stream().noneMatch(url::contains);
    }

    private String getCurrentDate() {
        final SimpleDateFormat sdf=new SimpleDateFormat("ddMMyyyyHHmmss");
        return sdf.format(new Date());
    }



}
