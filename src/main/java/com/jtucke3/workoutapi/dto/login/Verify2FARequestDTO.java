package com.jtucke3.workoutapi.dto.login;
public record Verify2FARequestDTO(String challengeId, String code) {}
