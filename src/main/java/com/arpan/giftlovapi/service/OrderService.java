package com.arpan.giftlovapi.service;

import com.arpan.giftlovapi.exception.ValidationException;
import com.arpan.giftlovapi.model.Order;
import com.arpan.giftlovapi.model.OrderStatus;
import com.arpan.giftlovapi.model.SimpleApiResponse;

import java.util.List;

public interface OrderService {
    SimpleApiResponse placeOrder(Order order) throws ValidationException;
    OrderStatus getOrderStatus(String orderId) throws ValidationException;
    List<Order> getALlOrders();
}
