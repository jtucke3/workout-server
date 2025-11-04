package com.jtucke3.workoutapi.service.workout.internal;

import com.jtucke3.workoutapi.dto.workout.*;

public interface IWorkoutInternalService {
    CreateWorkoutResponseDTO create(CreateWorkoutRequestDTO req);
    AddExerciseResponseDTO addExercise(AddExerciseRequestDTO req);
}
