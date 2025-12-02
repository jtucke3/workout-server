package com.jtucke3.workoutapi.service.workout.exercise.internal;

import com.jtucke3.workoutapi.dto.workout.exercise.AddSetRequestDTO;
import com.jtucke3.workoutapi.dto.workout.exercise.ExerciseResponseDTO;
import com.jtucke3.workoutapi.dto.workout.exercise.RemoveSetRequestDTO;

/**
 * Internal service interface for exercise operations.
 * Handles core business logic and returns updated ExerciseResponseDTO.
 */
public interface IWorkoutExerciseInternalService {
    ExerciseResponseDTO addSet(AddSetRequestDTO req);

    ExerciseResponseDTO removeSet(RemoveSetRequestDTO req);
}
