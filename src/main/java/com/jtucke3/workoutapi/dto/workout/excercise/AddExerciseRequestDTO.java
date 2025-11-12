package com.jtucke3.workoutapi.dto.workout.excercise;

import java.util.UUID;

public record AddExerciseRequestDTO(
        UUID workoutId,
        UUID catalogId,
        String name,
        String notes,
        Integer position
) {}
