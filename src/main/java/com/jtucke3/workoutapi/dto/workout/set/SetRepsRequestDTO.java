package com.jtucke3.workoutapi.dto.workout.set;

import java.util.UUID;

public record SetRepsRequestDTO(
    UUID setId,
    Integer reps
) {}
