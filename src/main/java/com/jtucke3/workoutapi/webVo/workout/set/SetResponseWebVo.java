package com.jtucke3.workoutapi.webVo.workout.set;

import java.time.Instant;
import java.util.UUID;

public record SetResponseWebVo(
    UUID setId,
    Double weight,
    Integer reps,
    Instant createdAt
) {}
