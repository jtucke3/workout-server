package com.jtucke3.workoutapi.dto.workout.set;

import java.util.UUID;

public record UpdateSetRequestDTO(
    UUID setId,
    Integer reps,
    Double weight
) {}
