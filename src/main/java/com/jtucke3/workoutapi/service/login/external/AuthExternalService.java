package com.jtucke3.workoutapi.service.login.external;

import com.jtucke3.workoutapi.dto.login.LoginRequestDTO;
import com.jtucke3.workoutapi.dto.login.LoginResponseDTO;
import com.jtucke3.workoutapi.service.login.internal.IAuthInternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthExternalService implements IAuthExternalService {

    private final IAuthInternalService internal;

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        var user = internal.validateCredentials(request.email(), request.password());
        var token = internal.issueToken(user);
        return new LoginResponseDTO(token, user);
    }
}
