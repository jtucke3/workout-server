package com.jtucke3.workoutapi.dto.mealTracking;

import java.time.Instant;
import java.util.UUID;

public record MealResponseDTO(
    UUID mealId,
    UUID userId,
    String name,
    int calories,
    Instant mealAtUtc,
    String notes,
    Instant createdAt
) {}
