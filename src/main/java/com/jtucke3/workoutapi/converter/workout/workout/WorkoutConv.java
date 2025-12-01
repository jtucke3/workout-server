package com.jtucke3.workoutapi.converter.workout.workout;

import java.util.UUID;
import java.util.stream.Collectors;

import com.jtucke3.workoutapi.converter.workout.exercise.ExerciseConv;
import com.jtucke3.workoutapi.dto.workout.workout.AddExerciseRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.CreateWorkoutRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.WorkoutResponseDTO;
import com.jtucke3.workoutapi.webVo.workout.workout.AddExerciseRequestWebVo;
import com.jtucke3.workoutapi.webVo.workout.workout.CreateWorkoutRequestWebVo;
import com.jtucke3.workoutapi.webVo.workout.workout.WorkoutResponseWebVo;

/**
 * Converter between WebVo and DTO for workout operations.
 */
public class WorkoutConv {

    public static CreateWorkoutRequestDTO toCreateWorkoutDTO(UUID userId, CreateWorkoutRequestWebVo webVo) {
        if (webVo == null) throw new IllegalArgumentException("Missing request body");
        var title = (webVo.getTitle() == null || webVo.getTitle().isBlank())
                ? "Untitled Workout" : webVo.getTitle().trim();
        return new CreateWorkoutRequestDTO(userId, title, webVo.getWorkoutAt(), webVo.getNotes());
    }

    public static AddExerciseRequestDTO toAddExerciseDTO(UUID workoutId, AddExerciseRequestWebVo webVo) {
        if (webVo == null) throw new IllegalArgumentException("Missing request body");
        return new AddExerciseRequestDTO(
                workoutId,
                webVo.getName(),
                webVo.getNotes(),
                webVo.getEquipment(),
                webVo.getBodyPart()
        );
    }

    public static WorkoutResponseWebVo toResponseWebVo(WorkoutResponseDTO dto) {
        if (dto == null) return null;

        var webVo = new WorkoutResponseWebVo();
        webVo.setId(dto.id());
        webVo.setTitle(dto.title());
        webVo.setNotes(dto.notes());
        webVo.setWorkoutAt(dto.workoutAt());
        webVo.setCreatedAt(dto.createdAt());

        if (dto.exercises() != null) {
            webVo.setExercises(
                dto.exercises().stream()
                    .map(ExerciseConv::toResponseWebVo)
                    .collect(Collectors.toList())
            );
        }

        return webVo;
    }
}
