package com.jtucke3.workoutapi.service.login.internal;

import com.jtucke3.workoutapi.dto.login.LoginResponseDTO;
import com.jtucke3.workoutapi.dto.login.RegisterRequestDTO;
import com.jtucke3.workoutapi.dto.login.Verify2FARequestDTO;
import com.jtucke3.workoutapi.dto.user.UserDTO;

public interface IAuthInternalService {
    UserDTO validateCredentials(String email, String rawPassword);
    String issueToken(UserDTO user);

    UserDTO register(RegisterRequestDTO req);

    LoginResponseDTO startLogin(String email, String password);
    LoginResponseDTO verify2fa(Verify2FARequestDTO req);

    String enable2faForUser(String email);
    void confirm2faSetup(String email, String code);
}
