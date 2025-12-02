package com.jtucke3.workoutapi.converter.workout.workout;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.jtucke3.workoutapi.converter.workout.exercise.ExerciseConv;
import com.jtucke3.workoutapi.dto.workout.workout.AddExerciseRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.CreateWorkoutRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.GetWorkoutRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.GetWorkoutsRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.UpdateWorkoutRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.WorkoutResponseDTO;
import com.jtucke3.workoutapi.webVo.workout.workout.AddExerciseRequestWebVo;
import com.jtucke3.workoutapi.webVo.workout.workout.CreateWorkoutRequestWebVo;
import com.jtucke3.workoutapi.webVo.workout.workout.GetWorkoutRequestWebVo;
import com.jtucke3.workoutapi.webVo.workout.workout.GetWorkoutsRequestWebVo;
import com.jtucke3.workoutapi.webVo.workout.workout.UpdateWorkoutRequestWebVo;
import com.jtucke3.workoutapi.webVo.workout.workout.WorkoutResponseWebVo;

/**
 * Converter between WebVo and DTO for workout operations.
 */
public class WorkoutConv {

    // --- WebVo → DTO ---

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

    public static GetWorkoutRequestDTO toGetWorkoutDTO(GetWorkoutRequestWebVo webVo) {
        if (webVo == null) throw new IllegalArgumentException("Missing request body");
        return new GetWorkoutRequestDTO(webVo.getWorkoutId());
    }

    public static GetWorkoutsRequestDTO toGetWorkoutsDTO(GetWorkoutsRequestWebVo webVo) {
        if (webVo == null) throw new IllegalArgumentException("Missing request body");
        return new GetWorkoutsRequestDTO(webVo.getUserId());
    }

    public static UpdateWorkoutRequestDTO toUpdateWorkoutDTO(UpdateWorkoutRequestWebVo webVo) {
        if (webVo == null) throw new IllegalArgumentException("Missing request body");
        var title = (webVo.getTitle() == null || webVo.getTitle().isBlank())
                ? "Untitled Workout" : webVo.getTitle().trim();
        return new UpdateWorkoutRequestDTO(webVo.getWorkoutId(), title, webVo.getWorkoutAt(), webVo.getNotes());
    }

    // --- DTO → WebVo ---

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

    public static List<WorkoutResponseWebVo> toResponseWebVoList(List<WorkoutResponseDTO> dtos) {
        if (dtos == null) return List.of();
        return dtos.stream()
                   .map(WorkoutConv::toResponseWebVo)
                   .collect(Collectors.toList());
    }
}
