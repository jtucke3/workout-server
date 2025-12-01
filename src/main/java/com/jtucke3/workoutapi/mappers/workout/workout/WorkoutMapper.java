package com.jtucke3.workoutapi.mappers.workout.workout;

import com.jtucke3.workoutapi.domain.entity.WorkoutEntity;
import com.jtucke3.workoutapi.dto.workout.workout.WorkoutResponseDTO;
import com.jtucke3.workoutapi.mappers.workout.exercise.ExerciseMapper;

public class WorkoutMapper {

    public static WorkoutResponseDTO toDTO(WorkoutEntity workout) {
        return new WorkoutResponseDTO(
            workout.getId(),
            workout.getTitle(),
            workout.getNotes(),
            workout.getWorkoutAt(),
            workout.getStatus(),
            workout.getCreatedAt(),
            workout.getExercises().stream()
                .map(ExerciseMapper::toDTO)
                .toList() 
        );
    }
}
