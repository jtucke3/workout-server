package com.jtucke3.workoutapi.service.login.external;

import com.jtucke3.workoutapi.dto.login.*;
import com.jtucke3.workoutapi.dto.user.UserDTO;

public interface IAuthExternalService {
    LoginResponseDTO login(LoginRequestDTO request);
    LoginResponseDTO verify2fa(Verify2FARequestDTO request);
    UserDTO register(RegisterRequestDTO request);
    String enable2faForCurrentUser(String email);
    public void confirm2faSetup(String email, String code);
}
