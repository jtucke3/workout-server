package com.jtucke3.workoutapi.dto.user;

import java.util.UUID;

public record ChangePasswordRequestDTO(
	UUID userId,
    String currentPassword,
    String newPassword
) {}
