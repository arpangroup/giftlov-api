package com.arpan.giftlovapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineItemDetails implements Serializable {
    private static final long serialVersionUID = 1234567L;

    int lineNumber;
    private String cardItemId;
    private double value;
    private String currency;
    private String status;
    private String statusDescription;
    private String claimURL;
    private String certificateGenerationKey;
    private String settlementCurrency;
    private String exchangeRate;
    private String settlementPrice;
    private String netPrice;
}
