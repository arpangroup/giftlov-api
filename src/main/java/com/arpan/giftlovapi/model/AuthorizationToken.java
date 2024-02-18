package com.arpan.giftlovapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuthorizationToken implements Serializable {
    private static final long serialVersionUID = 1234567L;

    private String token;
    private String fullName;
    private String organization;
    private String expireDate;
    private List<String> privileges;
    private Map<String, String> userInfo;
}
