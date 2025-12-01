package com.jtucke3.workoutapi.service.workout.set.external;

import org.springframework.stereotype.Service;

import com.jtucke3.workoutapi.dto.workout.set.SetResponseDTO;
import com.jtucke3.workoutapi.dto.workout.set.UpdateSetRequestDTO;
import com.jtucke3.workoutapi.service.workout.set.internal.IWorkoutSetInternalService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkoutSetExternalService implements IWorkoutSetExternalService {

    private final IWorkoutSetInternalService internal;

    @Override
    public SetResponseDTO updateSet(UpdateSetRequestDTO req) {
        return internal.updateSet(req);
    }
}
