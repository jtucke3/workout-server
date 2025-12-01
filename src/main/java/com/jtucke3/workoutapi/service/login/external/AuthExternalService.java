package com.jtucke3.workoutapi.service.login.external;

import com.jtucke3.workoutapi.dto.login.*;
import com.jtucke3.workoutapi.dto.user.UserDTO;
import com.jtucke3.workoutapi.service.login.internal.IAuthInternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthExternalService implements IAuthExternalService {

    private final IAuthInternalService internal;

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        return internal.startLogin(request.email(), request.password());
    }

    @Override
    public LoginResponseDTO verify2fa(Verify2FARequestDTO request) {
        return internal.verify2fa(request);
    }

    @Override
    public UserDTO register(RegisterRequestDTO request) {
        return internal.register(request);
    }

    @Override
    public String enable2faForCurrentUser(String email) {
        return internal.enable2faForUser(email);
    }

    @Override
    public void confirm2faSetup(String email, String code) {
        internal.confirm2faSetup(email, code);
    }

}
