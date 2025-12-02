package com.jtucke3.workoutapi.webVo.workout.workout;

import java.util.UUID;

/**
 * Web VO for requesting a single workout by its ID.
 * Complements GetWorkoutsRequestWebVo (plural).
 */
public class GetWorkoutRequestWebVo {

    private UUID workoutId;

    public GetWorkoutRequestWebVo() {}

    public GetWorkoutRequestWebVo(UUID workoutId) {
        this.workoutId = workoutId;
    }

    public UUID getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(UUID workoutId) {
        this.workoutId = workoutId;
    }
}
