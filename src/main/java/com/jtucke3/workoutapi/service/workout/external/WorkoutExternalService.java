package com.jtucke3.workoutapi.service.workout.external;

import com.jtucke3.workoutapi.dto.workout.*;
import com.jtucke3.workoutapi.service.workout.internal.IWorkoutInternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkoutExternalService implements IWorkoutExternalService {
    private final IWorkoutInternalService internal;

    @Override
    public CreateWorkoutResponseDTO create(CreateWorkoutRequestDTO req) {
        return internal.create(req);
    }

    @Override
    public AddExerciseResponseDTO addExercise(AddExerciseRequestDTO req) {
        return internal.addExercise(req);
    }
}
