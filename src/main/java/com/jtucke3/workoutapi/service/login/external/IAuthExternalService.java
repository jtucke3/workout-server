package com.jtucke3.workoutapi.service.login.external;

import com.jtucke3.workoutapi.dto.login.LoginRequestDTO;
import com.jtucke3.workoutapi.dto.login.LoginResponseDTO;

public interface IAuthExternalService {
    LoginResponseDTO login(LoginRequestDTO request);
}
