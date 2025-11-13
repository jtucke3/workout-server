package com.jtucke3.workoutapi.dto.workout.workout;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateWorkoutRequestDTO(
        UUID userId,
        String title,
        LocalDateTime workoutAt,
        String notes
) {}
