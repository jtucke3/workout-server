package com.jtucke3.workoutapi.service.workout.workout.external;

import org.springframework.stereotype.Service;

import com.jtucke3.workoutapi.dto.workout.exercise.AddExerciseRequestDTO;
import com.jtucke3.workoutapi.dto.workout.exercise.AddExerciseResponseDTO;
import com.jtucke3.workoutapi.dto.workout.workout.CreateWorkoutRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.CreateWorkoutResponseDTO;
import com.jtucke3.workoutapi.service.workout.workout.internal.IWorkoutInternalService;

import lombok.RequiredArgsConstructor;

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
