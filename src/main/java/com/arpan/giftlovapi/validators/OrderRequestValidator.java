package com.arpan.giftlovapi.validators;

import com.arpan.giftlovapi.exception.ValidationException;
import com.arpan.giftlovapi.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderRequestValidator {
    public boolean validate(Order order) throws ValidationException {
        //TODO: write yur own request validation logic
        return true;
    }
}
