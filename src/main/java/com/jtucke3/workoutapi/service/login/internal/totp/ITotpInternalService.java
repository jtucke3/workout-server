package com.jtucke3.workoutapi.service.login.internal.totp;

public interface ITotpInternalService {
    String newSecret();
    boolean isValid(String secret, String code);
    String otpauthUri(String issuer, String account, String secret);
}
