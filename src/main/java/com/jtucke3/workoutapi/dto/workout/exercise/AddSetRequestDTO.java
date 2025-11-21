package com.jtucke3.workoutapi.dto.workout.exercise;

import java.util.UUID;

public record AddSetRequestDTO(
    UUID exerciseId
) {}
