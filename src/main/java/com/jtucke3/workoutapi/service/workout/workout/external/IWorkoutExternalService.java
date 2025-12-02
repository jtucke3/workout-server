package com.jtucke3.workoutapi.service.workout.workout.external;

import java.util.List;
import java.util.UUID;

import com.jtucke3.workoutapi.dto.workout.workout.AddExerciseRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.CreateWorkoutRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.GetWorkoutRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.GetWorkoutsRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.RemoveExerciseRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.UpdateWorkoutRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.WorkoutResponseDTO;

/**
 * External service interface for workout operations.
 * Exposes DTOs for API layer use.
 */
public interface IWorkoutExternalService {

    // --- Core operations ---
    WorkoutResponseDTO create(CreateWorkoutRequestDTO req);
    WorkoutResponseDTO addExercise(AddExerciseRequestDTO req);
    WorkoutResponseDTO removeExercise(RemoveExerciseRequestDTO req);
    WorkoutResponseDTO updateWorkout(UpdateWorkoutRequestDTO req);

    /**
     * Remove a workout by its ID.
     * Returns void so the controller can decide the proper HTTP response (204 No Content, 404, etc.).
     */
    void removeWorkout(UUID workoutId);

    // --- Retrieval operations ---
    List<WorkoutResponseDTO> getWorkouts(GetWorkoutsRequestDTO req);
    WorkoutResponseDTO getWorkout(GetWorkoutRequestDTO req);

    // Optional convenience overload
    WorkoutResponseDTO getWorkoutById(UUID workoutId);
}
