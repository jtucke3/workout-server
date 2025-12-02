package com.jtucke3.workoutapi.webVo.mealTracking;

import java.time.Instant;
import java.util.UUID;

public record UpdateMealRequestWebVo(
    UUID mealId,
    UUID userId,
    String name,
    Integer calories,
    Instant mealAtUtc,
    String notes
) {}
