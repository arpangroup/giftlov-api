package com.arpan.giftlovapi.service.impl;

import com.arpan.giftlovapi.exception.ValidationException;
import com.arpan.giftlovapi.model.*;
import com.arpan.giftlovapi.service.OrderService;
import com.arpan.giftlovapi.validators.OrderRequestValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final RestTemplate restTemplate;
    private final OrderRequestValidator orderValidator;

    @Value("${endpoint.placeOrderUri:/placeOrder}")
    private String placeOrderUri;

    @Value("${catalog.disable-giftlov-api:true}")
    private boolean isGiftlovCatalogApiDisabled;

    @Override
    public SimpleApiResponse placeOrder(Order order) throws ValidationException {
        orderValidator.validate(order);
        if (isGiftlovCatalogApiDisabled) { // Dummy response just for testing
            return new SimpleApiResponse("GIFLOV-001872", UUID.randomUUID().toString());
        }
        final SimpleApiResponse orderResponse = proceedOrder(order);
        return orderResponse;
    }

    @Override
    public OrderStatus getOrderStatus(String orderId) throws ValidationException {
        return null;
    }

    @Override
    public List<Order> getALlOrders() {
        return null;
    }

    private SimpleApiResponse proceedOrder(Order order) throws ValidationException{
        try {
            ResponseEntity<SimpleApiResponse> response = restTemplate.postForEntity(placeOrderUri, order, SimpleApiResponse.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            throw  new ValidationException(e.getMessage());
        }
    }


}
