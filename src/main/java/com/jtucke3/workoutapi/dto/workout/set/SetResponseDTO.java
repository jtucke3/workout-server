package com.jtucke3.workoutapi.dto.workout.set;

import java.time.Instant;
import java.util.UUID;

public record SetResponseDTO(
    UUID setId,
    Double weight,
    Integer reps,
    Instant createdAt
) {}
