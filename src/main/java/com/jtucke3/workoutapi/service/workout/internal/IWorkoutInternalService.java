package com.jtucke3.workoutapi.service.workout.internal;

import com.jtucke3.workoutapi.dto.workout.excercise.AddExerciseRequestDTO;
import com.jtucke3.workoutapi.dto.workout.excercise.AddExerciseResponseDTO;
import com.jtucke3.workoutapi.dto.workout.workout.CreateWorkoutRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.CreateWorkoutResponseDTO;

public interface IWorkoutInternalService {
    CreateWorkoutResponseDTO create(CreateWorkoutRequestDTO req);
    AddExerciseResponseDTO addExercise(AddExerciseRequestDTO req);
}
