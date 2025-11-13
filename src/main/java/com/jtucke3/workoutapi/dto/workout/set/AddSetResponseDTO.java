package com.jtucke3.workoutapi.dto.workout.set;

import java.util.UUID;

public record AddSetResponseDTO(
    UUID setId,
    UUID exerciseId,
    Double weight,
    Integer reps
) {}
