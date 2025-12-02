package com.jtucke3.workoutapi.dto.workout.workout;

import java.util.UUID;

/**
 * DTO for requesting all workouts belonging to a user.
 * Mirrors GetWorkoutsRequestWebVo for service layer use.
 */
public class GetWorkoutsRequestDTO {

    private UUID userId;

    public GetWorkoutsRequestDTO() {}

    public GetWorkoutsRequestDTO(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
