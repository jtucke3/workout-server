package com.jtucke3.workoutapi.dto.workout.set;

import java.util.UUID;

public record SetWeightResponseDTO(
    UUID setId,
    Double weight
) {}
