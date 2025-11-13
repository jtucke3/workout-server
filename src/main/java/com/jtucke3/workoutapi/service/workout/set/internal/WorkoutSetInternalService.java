package com.jtucke3.workoutapi.service.workout.set.internal;

import org.springframework.stereotype.Service;

import com.jtucke3.workoutapi.dao.workout.IWorkoutSetDao;
import com.jtucke3.workoutapi.domain.entity.WorkoutSetEntity;
import com.jtucke3.workoutapi.dto.workout.set.SetRepsRequestDTO;
import com.jtucke3.workoutapi.dto.workout.set.SetRepsResponseDTO;
import com.jtucke3.workoutapi.dto.workout.set.SetWeightRequestDTO;
import com.jtucke3.workoutapi.dto.workout.set.SetWeightResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkoutSetInternalService implements IWorkoutSetInternalService {

    private final IWorkoutSetDao dao;

    @Override
    public SetWeightResponseDTO setWeight(SetWeightRequestDTO req) {
        WorkoutSetEntity set = dao.findById(req.setId())
            .orElseThrow(() -> new IllegalArgumentException("Set not found: " + req.setId()));
        set.setWeight(req.weight());
        return new SetWeightResponseDTO(set.getId(), set.getWeight());
    }

    @Override
    public SetRepsResponseDTO setReps(SetRepsRequestDTO req) {
        WorkoutSetEntity set = dao.findById(req.setId())
            .orElseThrow(() -> new IllegalArgumentException("Set not found: " + req.setId()));
        set.setReps(req.reps());
        return new SetRepsResponseDTO(set.getId(), set.getReps());
    }
}
