package com.jtucke3.workoutapi.dto.login;

import com.jtucke3.workoutapi.dto.user.UserDTO;

public record LoginResponseDTO(String token, UserDTO user, boolean requires2FA, String challengeId) {
    public static LoginResponseDTO token(UserDTO u, String t) {
        return new LoginResponseDTO(t, u, false, null);
    }
    public static LoginResponseDTO needs2FA(String challengeId) {
        return new LoginResponseDTO(null, null, true, challengeId);
    }
}
