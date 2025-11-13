package com.jtucke3.workoutapi.service.workout.set.external;

import org.springframework.stereotype.Service;

import com.jtucke3.workoutapi.dto.workout.set.SetRepsRequestDTO;
import com.jtucke3.workoutapi.dto.workout.set.SetRepsResponseDTO;
import com.jtucke3.workoutapi.dto.workout.set.SetWeightRequestDTO;
import com.jtucke3.workoutapi.dto.workout.set.SetWeightResponseDTO;
import com.jtucke3.workoutapi.service.workout.set.internal.IWorkoutSetInternalService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkoutSetExternalService implements IWorkoutSetExternalService {

    private final IWorkoutSetInternalService internal;

    @Override
    public SetWeightResponseDTO setWeight(SetWeightRequestDTO req) {
        return internal.setWeight(req);
    }

    @Override
    public SetRepsResponseDTO setReps(SetRepsRequestDTO req) {
        return internal.setReps(req);
    }
}
