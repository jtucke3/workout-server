package com.jtucke3.workoutapi.service.workout.workout.internal;

import com.jtucke3.workoutapi.dto.workout.workout.AddExerciseRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.CreateWorkoutRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.RemoveExerciseRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.WorkoutResponseDTO;

/**
 * Internal service interface for workout operations.
 * Handles core business logic and returns unified WorkoutResponseDTO.
 */
public interface IWorkoutInternalService {
    WorkoutResponseDTO create(CreateWorkoutRequestDTO req);

    WorkoutResponseDTO addExercise(AddExerciseRequestDTO req);

    WorkoutResponseDTO removeExercise(RemoveExerciseRequestDTO req);
}
