package com.jtucke3.workoutapi.service.workout.workout.external;

import com.jtucke3.workoutapi.dto.workout.exercise.AddExerciseRequestDTO;
import com.jtucke3.workoutapi.dto.workout.exercise.AddExerciseResponseDTO;
import com.jtucke3.workoutapi.dto.workout.workout.CreateWorkoutRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.CreateWorkoutResponseDTO;

public interface IWorkoutExternalService {
    CreateWorkoutResponseDTO create(CreateWorkoutRequestDTO req);
    AddExerciseResponseDTO addExercise(AddExerciseRequestDTO req);
}

