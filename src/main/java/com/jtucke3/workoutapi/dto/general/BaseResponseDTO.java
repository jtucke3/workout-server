package com.jtucke3.workoutapi.dto.general;

public record BaseResponseDTO(
    boolean success,
    String message
) {}
