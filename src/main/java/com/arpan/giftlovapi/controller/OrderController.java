package com.arpan.giftlovapi.controller;

import com.arpan.giftlovapi.exception.ValidationException;
import com.arpan.giftlovapi.model.Order;
import com.arpan.giftlovapi.model.SimpleApiResponse;
import com.arpan.giftlovapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<SimpleApiResponse> submitOrder (@RequestBody Order orderRequest) throws ValidationException {
        return ResponseEntity.ok(orderService.placeOrder(orderRequest));
    }
}
