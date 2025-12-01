package com.jtucke3.workoutapi.webVo.login;

public class Confirm2FASetupWebRequestWebVo {
    private String email;
    private String code; // keep as string so leading zeros aren't lost

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
}
