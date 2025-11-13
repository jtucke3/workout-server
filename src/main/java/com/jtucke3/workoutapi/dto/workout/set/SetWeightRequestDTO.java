package com.jtucke3.workoutapi.dto.workout.set;

import java.util.UUID;

public record SetWeightRequestDTO(
    UUID setId,
    Double weight
) {}
