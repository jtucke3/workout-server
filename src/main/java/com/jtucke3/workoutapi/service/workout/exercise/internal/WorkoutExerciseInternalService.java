package com.jtucke3.workoutapi.service.workout.exercise.internal;

import org.springframework.stereotype.Service;

import com.jtucke3.workoutapi.dao.workout.IWorkoutExerciseDao;
import com.jtucke3.workoutapi.domain.entity.WorkoutSetEntity;
import com.jtucke3.workoutapi.dto.workout.set.AddSetRequestDTO;
import com.jtucke3.workoutapi.dto.workout.set.AddSetResponseDTO;
import com.jtucke3.workoutapi.dto.workout.set.RemoveSetRequestDTO;
import com.jtucke3.workoutapi.dto.workout.set.RemoveSetResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkoutExerciseInternalService implements IWorkoutExerciseInternalService {

    private final IWorkoutExerciseDao dao;

    @Override
    public AddSetResponseDTO addSet(AddSetRequestDTO req) {
        WorkoutSetEntity set = dao.addSet(req.exerciseId());
        return new AddSetResponseDTO(
            set.getId(),
            set.getExercise().getId(),
            set.getWeight(),
            set.getReps()
        );
    }

    @Override
    public RemoveSetResponseDTO removeSet(RemoveSetRequestDTO req) {
        dao.removeSet(req.setId()); // impl a return on it later
        return new RemoveSetResponseDTO(req.setId(), true);
    }
}
