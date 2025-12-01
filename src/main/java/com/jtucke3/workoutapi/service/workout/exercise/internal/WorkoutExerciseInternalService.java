package com.jtucke3.workoutapi.service.workout.exercise.internal;

import org.springframework.stereotype.Service;

import com.jtucke3.workoutapi.dao.workout.IWorkoutExerciseDao;
import com.jtucke3.workoutapi.domain.entity.WorkoutSetEntity;
import com.jtucke3.workoutapi.dto.workout.exercise.AddSetRequestDTO;
import com.jtucke3.workoutapi.dto.workout.exercise.ExerciseResponseDTO;
import com.jtucke3.workoutapi.dto.workout.exercise.RemoveSetRequestDTO;
import com.jtucke3.workoutapi.mappers.workout.exercise.ExerciseMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkoutExerciseInternalService implements IWorkoutExerciseInternalService {

    private final IWorkoutExerciseDao dao;

    @Override
    public ExerciseResponseDTO addSet(AddSetRequestDTO req) {
        WorkoutSetEntity set = dao.addSet(req.exerciseId());
        return ExerciseMapper.toDTO(set.getExercise());
    }

    @Override
    public ExerciseResponseDTO removeSet(RemoveSetRequestDTO req) {
        // Find the set by ID
        WorkoutSetEntity set = dao.findSetById(req.setId())
                .orElseThrow(() -> new IllegalArgumentException("Set not found"));

        // Grab the parent exercise before deleting
        var exercise = set.getExercise();

        // Remove the set
        dao.removeSet(req.setId());

        // Return the updated exercise mapped to DTO
        return ExerciseMapper.toDTO(exercise);
    }

}
