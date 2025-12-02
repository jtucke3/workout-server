package com.jtucke3.workoutapi.service.workout.exercise.external;

import org.springframework.stereotype.Service;

import com.jtucke3.workoutapi.dto.workout.exercise.AddSetRequestDTO;
import com.jtucke3.workoutapi.dto.workout.exercise.ExerciseResponseDTO;
import com.jtucke3.workoutapi.dto.workout.exercise.RemoveSetRequestDTO;
import com.jtucke3.workoutapi.service.workout.exercise.internal.IWorkoutExerciseInternalService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkoutExerciseExternalService implements IWorkoutExerciseExternalService {

    private final IWorkoutExerciseInternalService internal;

    @Override
    public ExerciseResponseDTO addSet(AddSetRequestDTO req) {
        return internal.addSet(req);  // ✅ returns updated ExerciseResponseDTO
    }

    @Override
    public ExerciseResponseDTO removeSet(RemoveSetRequestDTO req) {
        return internal.removeSet(req);  // ✅ returns updated ExerciseResponseDTO
    }
}
