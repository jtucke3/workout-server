package com.jtucke3.workoutapi.dto.workout.workout;

import java.util.UUID;

/**
 * Request DTO for removing an exercise from a workout.
 */
public record RemoveExerciseRequestDTO(
        UUID workoutId,
        UUID exerciseId
) {}
