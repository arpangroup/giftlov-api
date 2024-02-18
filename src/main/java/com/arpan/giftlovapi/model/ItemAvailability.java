package com.arpan.giftlovapi.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemAvailability implements Serializable {
    private static final long serialVersionUID = 1234567L;

    private Boolean available;
    private Integer code;
    private String message;
}
