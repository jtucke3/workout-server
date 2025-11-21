package com.jtucke3.workoutapi.service.workout.exercise.external;

import com.jtucke3.workoutapi.dto.workout.exercise.AddSetRequestDTO;
import com.jtucke3.workoutapi.dto.workout.exercise.AddSetResponseDTO;
import com.jtucke3.workoutapi.dto.workout.exercise.RemoveSetRequestDTO;
import com.jtucke3.workoutapi.dto.workout.exercise.RemoveSetResponseDTO;

public interface IWorkoutExerciseExternalService {
    AddSetResponseDTO addSet(AddSetRequestDTO req);
    RemoveSetResponseDTO removeSet(RemoveSetRequestDTO req);
}
