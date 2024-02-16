package com.arpan.giftlovapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
class LineItem implements Serializable {
    private static final long serialVersionUID = 1234567L;

    private String cardItemId;
    private double value;
}
