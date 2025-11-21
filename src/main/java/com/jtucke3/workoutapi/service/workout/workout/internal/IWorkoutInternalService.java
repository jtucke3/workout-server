package com.jtucke3.workoutapi.service.workout.workout.internal;

import com.jtucke3.workoutapi.dto.workout.workout.AddExerciseRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.AddExerciseResponseDTO;
import com.jtucke3.workoutapi.dto.workout.workout.CreateWorkoutRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.CreateWorkoutResponseDTO;

public interface IWorkoutInternalService {
    CreateWorkoutResponseDTO create(CreateWorkoutRequestDTO req);
    AddExerciseResponseDTO addExercise(AddExerciseRequestDTO req);
}
