package com.arpan.giftlovapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class SimpleApiResponse  implements Serializable {
    private static final long serialVersionUID = 1234567L;

    private String id;
    private String referenceNo;
}
