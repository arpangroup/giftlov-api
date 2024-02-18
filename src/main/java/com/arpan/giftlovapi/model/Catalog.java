package com.arpan.giftlovapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Catalog implements Serializable {
    private static final long serialVersionUID = 1234567L;

    List<Item> items;
    private int total;
    private int rowCount;
    private int current;
}
