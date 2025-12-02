package com.jtucke3.workoutapi.service.workout.workout.internal;

import java.util.List;
import java.util.UUID;

import com.jtucke3.workoutapi.dto.workout.workout.AddExerciseRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.CreateWorkoutRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.RemoveExerciseRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.UpdateWorkoutRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.WorkoutResponseDTO;

/**
 * Internal service interface for workout operations.
 * Handles core business logic and returns unified WorkoutResponseDTO.
 */
public interface IWorkoutInternalService {

    // --- Core operations ---
    WorkoutResponseDTO create(CreateWorkoutRequestDTO req);

    WorkoutResponseDTO addExercise(AddExerciseRequestDTO req);

    WorkoutResponseDTO removeExercise(RemoveExerciseRequestDTO req);

    WorkoutResponseDTO updateWorkout(UpdateWorkoutRequestDTO req); // <-- new

    // --- Retrieval operations ---
    /**
     * Find all workouts belonging to a specific user.
     */
    List<WorkoutResponseDTO> findByUserId(UUID userId);

    /**
     * Find a single workout by its ID.
     */
    WorkoutResponseDTO findById(UUID workoutId);
}
