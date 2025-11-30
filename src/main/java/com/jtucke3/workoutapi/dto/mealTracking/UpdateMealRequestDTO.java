package com.jtucke3.workoutapi.dto.mealTracking;

import java.time.Instant;
import java.util.UUID;

public record UpdateMealRequestDTO(
    UUID mealId,
    String name,
    Integer calories,
    Instant mealAtUtc,   // renamed for consistency with entity
    String notes
) {}
