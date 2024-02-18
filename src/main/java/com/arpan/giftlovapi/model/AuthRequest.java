package com.arpan.giftlovapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest implements Serializable {
    private static final long serialVersionUID = 1234567L;

    private String username;
    private String password;
}
