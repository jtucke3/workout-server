package com.jtucke3.workoutapi.dto.workout;

import java.util.UUID;

public record ExerciseCatalogItemDTO(
        UUID id,
        String name,
        String bodyPart,
        String equipment
) {}
