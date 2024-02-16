package com.arpan.giftlovapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String id;
    private String name;
    private String usageInstructions;
    private String brand;
    private String termsAndConditions;
    private String giftCardInformation;
    private int fromValue = 1;
    private int toValue;
    private String currency;
    private String cardFaceImage;
    private String cardFaceHash;
    private int productId;
    private String discount;
    private String activationFees;
    private String fulfillmentFees;
    private String shippingFees;
    private String customizationFees;
    private String refundFees;
    private String otherFees;
    private String reconciliationCurrency;
    private Double exchangeRate;
    private List<String> categories;
    private String countryCode;
}