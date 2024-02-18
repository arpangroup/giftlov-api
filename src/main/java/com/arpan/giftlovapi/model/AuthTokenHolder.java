package com.arpan.giftlovapi.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Data
public class AuthTokenHolder {
    private String accessToken;
    private String refreshToken;
    private String secret;

    private String fullName;
    private String organization;
    private String expireDate;
    private List<String> privileges;
    private Map<String, String> userInfo;

    public void setAuthorizationToken(AuthorizationToken authToken) {
        this.accessToken = authToken.getToken();
        this.refreshToken = null;
        this.secret = null;
        this.fullName = authToken.getFullName();
        this.organization = authToken.getOrganization();
        this.expireDate = authToken.getExpireDate();
        this.privileges = authToken.getPrivileges();
        this.userInfo = authToken.getUserInfo();
    }
}
