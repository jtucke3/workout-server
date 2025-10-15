package com.jtucke3.workoutapi.service.login.internal;

import com.jtucke3.workoutapi.dto.user.UserDTO;

public interface IAuthInternalService {
    UserDTO validateCredentials(String email, String rawPassword);
    String issueToken(UserDTO user);
}
