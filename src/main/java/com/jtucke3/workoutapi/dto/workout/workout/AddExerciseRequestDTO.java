package com.jtucke3.workoutapi.dto.workout.workout;

import java.util.UUID;

/**
 * Request DTO for adding a new exercise to a workout.
 * Mirrors WorkoutExerciseEntity fields that should come from the client.
 */
public record AddExerciseRequestDTO(
        UUID workoutId,
        String name,
        String notes,
        String equipment,
        String bodyPart
) {}
