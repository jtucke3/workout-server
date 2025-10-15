package com.jtucke3.workoutapi.webVo.login;

import java.util.UUID;

public class LoginWebResponseWebVo {
    private String token;
    private UUID userId;
    private String email;
    private String displayName;

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
}
