package com.jtucke3.workoutapi.dto.workout.set;

import java.util.UUID;

public record SetRepsResponseDTO(
    UUID setId,
    Integer reps
) {}
