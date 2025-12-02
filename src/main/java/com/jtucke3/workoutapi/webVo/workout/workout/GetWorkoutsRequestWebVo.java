package com.jtucke3.workoutapi.webVo.workout.workout;

import java.util.UUID;

/**
 * Web VO for requesting all workouts belonging to a user.
 * Can be converted into GetWorkoutsRequestDTO for service layer use.
 */
public class GetWorkoutsRequestWebVo {
    private UUID userId;

    public UUID getUserId() { 
        return userId; 
    }

    public void setUserId(UUID userId) { 
        this.userId = userId; 
    }
}
