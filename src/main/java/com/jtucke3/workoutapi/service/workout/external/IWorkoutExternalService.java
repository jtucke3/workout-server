package com.jtucke3.workoutapi.service.workout.external;

import com.jtucke3.workoutapi.dto.workout.*;

public interface IWorkoutExternalService {
    CreateWorkoutResponseDTO create(CreateWorkoutRequestDTO req);
    AddExerciseResponseDTO addExercise(AddExerciseRequestDTO req);
}
