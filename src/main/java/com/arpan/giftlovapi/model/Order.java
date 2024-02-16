package com.arpan.giftlovapi.model;

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

    private String customerName;
    private String firstName;
    private String lastName;
    private String referenceNo;
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