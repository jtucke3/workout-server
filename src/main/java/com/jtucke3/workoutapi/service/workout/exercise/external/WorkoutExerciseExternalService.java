package com.jtucke3.workoutapi.service.workout.exercise.external;

import org.springframework.stereotype.Service;

import com.jtucke3.workoutapi.dto.workout.set.AddSetRequestDTO;
import com.jtucke3.workoutapi.dto.workout.set.AddSetResponseDTO;
import com.jtucke3.workoutapi.dto.workout.set.RemoveSetRequestDTO;
import com.jtucke3.workoutapi.dto.workout.set.RemoveSetResponseDTO;
import com.jtucke3.workoutapi.service.workout.exercise.internal.IWorkoutExerciseInternalService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkoutExerciseExternalService implements IWorkoutExerciseExternalService {

    private final IWorkoutExerciseInternalService internal;

    @Override
    public AddSetResponseDTO addSet(AddSetRequestDTO req) {
        return internal.addSet(req);
    }

    @Override
    public RemoveSetResponseDTO removeSet(RemoveSetRequestDTO req) {
        return internal.removeSet(req);
    }
}
