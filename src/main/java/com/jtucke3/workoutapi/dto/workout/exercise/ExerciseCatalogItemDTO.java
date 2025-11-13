package com.jtucke3.workoutapi.dto.workout.exercise;

import java.util.UUID;

public record ExerciseCatalogItemDTO(
        UUID id,
        String name,
        String bodyPart,
        String equipment
) {}
