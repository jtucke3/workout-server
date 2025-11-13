package com.jtucke3.workoutapi.service.workout.exercise.internal;

import com.jtucke3.workoutapi.dto.workout.set.AddSetRequestDTO;
import com.jtucke3.workoutapi.dto.workout.set.AddSetResponseDTO;
import com.jtucke3.workoutapi.dto.workout.set.RemoveSetRequestDTO;
import com.jtucke3.workoutapi.dto.workout.set.RemoveSetResponseDTO;

public interface IWorkoutExerciseInternalService {
    AddSetResponseDTO addSet(AddSetRequestDTO req);
    RemoveSetResponseDTO removeSet(RemoveSetRequestDTO req);
}
