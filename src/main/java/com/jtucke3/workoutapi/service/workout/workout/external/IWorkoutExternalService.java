package com.jtucke3.workoutapi.service.workout.workout.external;

import com.jtucke3.workoutapi.dto.workout.workout.AddExerciseRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.CreateWorkoutRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.RemoveExerciseRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.WorkoutResponseDTO;


public interface IWorkoutExternalService {
    WorkoutResponseDTO create(CreateWorkoutRequestDTO req);
    WorkoutResponseDTO addExercise(AddExerciseRequestDTO req);
    WorkoutResponseDTO removeExercise(RemoveExerciseRequestDTO req);
}

