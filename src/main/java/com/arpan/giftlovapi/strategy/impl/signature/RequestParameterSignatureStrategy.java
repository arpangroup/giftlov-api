package com.arpan.giftlovapi.strategy.impl.signature;

import com.arpan.giftlovapi.strategy.RequestParamsExtractionStrategy;
import com.arpan.giftlovapi.strategy.SignatureStrategy;
import com.arpan.giftlovapi.strategy.impl.requestparam.GetRequestParametersExtractionStrategy;
import com.arpan.giftlovapi.strategy.impl.requestparam.PostRequestParametersExtractionStrategy;
import com.arpan.giftlovapi.strategy.impl.requestparam.RequestBodyParamsExtractorHelper;
import com.arpan.giftlovapi.util.SecureUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;

@Slf4j
@RequiredArgsConstructor
public class RequestParameterSignatureStrategy implements SignatureStrategy {
    private final String baseUrl;
    private final HttpRequest request;
    private final byte[] requestBody;
    private final RequestBodyParamsExtractorHelper requestBodyParamsExtractorHelper;
    private final String giftlodDateHeader;
    private final String accessToken;
    private final String salt;

    @Override
    public String generateSignature() {
        final RequestParamsExtractionStrategy paramsExtractionStrategy = gerParamsExtractionStrategy(request.getMethod());
        final String requestPart = getRequestPart(baseUrl, request.getURI().toString());
        final String methodName = request.getMethod().name().toUpperCase();
        final String params = paramsExtractionStrategy.getSortedParams();
        final String signature = requestPart + methodName + params + giftlodDateHeader + accessToken;
        final String hashedSignature = SecureUtils.get_SHA_512_SecurePassword(signature, salt);
        log.info("===========================signature================================================");
        log.debug("RequestPart : {}", requestPart);
        log.debug("MethodName  : {}", methodName);
        log.debug("Parameters  : {}", params);
        log.debug("GiftlovDate : {}", giftlodDateHeader);
        log.debug("AccessToken : {}", accessToken);
        log.debug("Signature   : {}", signature);
        log.debug("Final       : {}", hashedSignature);
        log.info("==========================signature end================================================");
        return signature;
    }

    private RequestParamsExtractionStrategy gerParamsExtractionStrategy (final HttpMethod httpMethod) {
        RequestParamsExtractionStrategy paramsExtractionStrategy = new GetRequestParametersExtractionStrategy(request.getURI());
        if (httpMethod == HttpMethod.POST) {
            paramsExtractionStrategy = new PostRequestParametersExtractionStrategy(requestBody, requestBodyParamsExtractorHelper);
        }
        return paramsExtractionStrategy;
    }

    private String getRequestPart(final String baseUrl, final String fullUrl) {
        if (fullUrl == null || baseUrl == null) return "";
        final String requestUrl = fullUrl.replace(baseUrl, "").trim();
        String requestPart = requestUrl.split("\\?")[0];
        return requestPart.substring(1);//remove slash("/") from start
    }
}
