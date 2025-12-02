package com.jtucke3.workoutapi.dto.workout.workout;

import java.util.UUID;

/**
 * DTO for requesting a single workout by its ID.
 * Complements GetWorkoutsRequestDTO (plural).
 */
public class GetWorkoutRequestDTO {

    private UUID workoutId;

    public GetWorkoutRequestDTO() {}

    public GetWorkoutRequestDTO(UUID workoutId) {
        this.workoutId = workoutId;
    }

    public UUID getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(UUID workoutId) {
        this.workoutId = workoutId;
    }
}
