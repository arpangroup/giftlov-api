package com.arpan.giftlovapi.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
    private static final long serialVersionUID = 1234567L;

    @Min(value = 4, message = "customerName should be at least 5 characters long")
    private String customerName;
    @Min(value = 2, message = "firstName should be at least 4 characters long")
    private String firstName;
    @Min(value = 2, message = "lastName should be at least 4 characters long")
    private String lastName;
    private String referenceNo;
    @NotNull
    private String deliveryChannel;

    private String contactNumber;
    private String smsMobileNumber;
    private String emailAddress;
    private Map<String, String> additionalParameters;
    private String countryCode = "AE";
    private String languageCode = "EN";
    private String orderDate;//yyyy-MM-dd.//2022-05-15

    private List<OrderLineItem> lineItems;
}