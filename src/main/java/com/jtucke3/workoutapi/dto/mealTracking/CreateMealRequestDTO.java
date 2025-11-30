package com.jtucke3.workoutapi.dto.mealTracking;

import java.time.Instant;
import java.util.UUID;

public record CreateMealRequestDTO(
    UUID userId,
    String name,
    int calories,
    Instant mealAtUtc,   // renamed for consistency with entity
    String notes
) {}
