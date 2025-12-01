package com.jtucke3.workoutapi.dto.login;

import com.jtucke3.workoutapi.dto.user.UserDTO;

public record LoginResponseDTO(
        String token,
        UserDTO user,
        boolean requires2FA,
        String challengeId,
        boolean hasTwoFactorConfigured
) {
    public static LoginResponseDTO needs2FA(String challengeId, boolean hasTwoFactorConfigured) {
        return new LoginResponseDTO(null, null, true, challengeId, hasTwoFactorConfigured);
    }

    public static LoginResponseDTO token(UserDTO user, String token, boolean hasTwoFactorConfigured) {
        return new LoginResponseDTO(token, user, false, null, hasTwoFactorConfigured);
    }
}
