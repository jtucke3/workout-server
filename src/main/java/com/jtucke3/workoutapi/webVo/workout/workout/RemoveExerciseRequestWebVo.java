package com.jtucke3.workoutapi.webVo.workout.workout;

import java.util.UUID;

/**
 * Web VO for removing an exercise from a workout.
 * Can be converted into RemoveExerciseRequestDTO for service layer use.
 */
public class RemoveExerciseRequestWebVo {
    private UUID workoutId;
    private UUID exerciseId;

    public UUID getWorkoutId() { return workoutId; }
    public void setWorkoutId(UUID workoutId) { this.workoutId = workoutId; }

    public UUID getExerciseId() { return exerciseId; }
    public void setExerciseId(UUID exerciseId) { this.exerciseId = exerciseId; }
}
