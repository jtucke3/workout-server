package com.jtucke3.workoutapi.webVo.mealTracking;

import java.time.Instant;
import java.util.UUID;

public record CreateMealRequestWebVo(
    String userEmail,
    String name,
    int calories,
    Instant mealAtUtc,
    String notes
) {}
