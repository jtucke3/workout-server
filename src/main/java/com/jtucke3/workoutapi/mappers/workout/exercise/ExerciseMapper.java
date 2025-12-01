package com.jtucke3.workoutapi.mappers.workout.exercise;

import java.util.stream.Collectors;

import com.jtucke3.workoutapi.domain.entity.WorkoutExerciseEntity;
import com.jtucke3.workoutapi.dto.workout.exercise.ExerciseResponseDTO;
import com.jtucke3.workoutapi.mappers.workout.set.SetMapper;

public class ExerciseMapper {

    public static ExerciseResponseDTO toDTO(WorkoutExerciseEntity exercise) {
        return new ExerciseResponseDTO(
            exercise.getId(),
            exercise.getName(),
            exercise.getNotes(),
            exercise.getEquipment(),
            exercise.getBodyPart(),
            exercise.getCreatedAt(),
            exercise.getSets().stream()
                .map(SetMapper::toDTO)
                .collect(Collectors.toList())
        );
    }
}
