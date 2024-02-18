package com.arpan.giftlovapi.config;

import com.arpan.giftlovapi.exception.DownstreamApiException;
import com.arpan.giftlovapi.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.*;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is5xxServerError() ||
                response.getStatusCode().is4xxClientError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is5xxServerError()) {
            //Handle SERVER_ERROR
            throw new HttpClientErrorException(response.getStatusCode());
        } else if (response.getStatusCode().is4xxClientError()) {
            //Handle CLIENT_ERROR
            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                //throw new NotFoundException();
                log.debug("NotFoundException....");
            }
        }
    }

    private void getErrorBody(ClientHttpResponse response) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBody()))) {
            String httpBodyResponse = reader.lines().collect(Collectors.joining(""));

            // TODO deserialize (could be JSON, XML, whatever...) httpBodyResponse to a POJO that matches the error structure for that specific API, then extract the error message.
            // Here the whole response will be treated as the error message, you probably don't want that.
            String errorMessage = httpBodyResponse;

            ObjectMapper mapper = new ObjectMapper();
            ErrorResponse errorResponse = mapper.readValue(errorMessage, ErrorResponse.class);

            throw new DownstreamApiException(errorResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
