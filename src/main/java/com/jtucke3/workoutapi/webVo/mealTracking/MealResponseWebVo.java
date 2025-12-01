package com.jtucke3.workoutapi.webVo.mealTracking;

import java.time.Instant;
import java.util.UUID;

public record MealResponseWebVo(
    UUID id,
    UUID userId,
    String name,
    int calories,
    Instant mealAtUtc,
    String notes,
    Instant createdAt
) {}
