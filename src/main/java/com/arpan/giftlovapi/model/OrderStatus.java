package com.arpan.giftlovapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
@AllArgsConstructor
public class OrderStatus implements Serializable {
    private static final long serialVersionUID = 1234567L;

    private String id;
    private String customerName;
    private String deliveryChannel;
    private String emailAddress;
    private String smsMobileNumber;
    private String referenceNo;
    private String creationDate;
    private String placementDate;
    private List<LineItemDetails>lineItems;
}
