package com.jtucke3.workoutapi.service.workout.exercise.external;

import com.jtucke3.workoutapi.dto.workout.exercise.AddSetRequestDTO;
import com.jtucke3.workoutapi.dto.workout.exercise.ExerciseResponseDTO;
import com.jtucke3.workoutapi.dto.workout.exercise.RemoveSetRequestDTO;

public interface IWorkoutExerciseExternalService {
    ExerciseResponseDTO addSet(AddSetRequestDTO req);
    ExerciseResponseDTO removeSet(RemoveSetRequestDTO req);
}
