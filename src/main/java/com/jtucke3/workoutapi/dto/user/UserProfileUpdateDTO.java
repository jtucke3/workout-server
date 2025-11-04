package com.jtucke3.workoutapi.dto.user;

public record UserProfileUpdateDTO(
    String email,
    String displayName,
    Boolean twoFactorEnabled
) {}
