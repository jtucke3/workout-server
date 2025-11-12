package com.jtucke3.workoutapi.converter.workout;

import java.util.UUID;

import com.jtucke3.workoutapi.dto.workout.excercise.AddExerciseRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.CreateWorkoutRequestDTO;
import com.jtucke3.workoutapi.webVo.workout.AddExerciseWebVo;
import com.jtucke3.workoutapi.webVo.workout.CreateWorkoutWebVo;

public class WorkoutConv {

    public static CreateWorkoutRequestDTO toCreateWorkoutDTO(UUID userId, CreateWorkoutWebVo webVo) {
        if (webVo == null) throw new IllegalArgumentException("Missing request body");
        var title = (webVo.getTitle() == null || webVo.getTitle().isBlank())
                ? "Untitled Workout" : webVo.getTitle().trim();
        return new CreateWorkoutRequestDTO(userId, title, webVo.getWorkoutAt(), webVo.getNotes());
    }

    public static AddExerciseRequestDTO toAddExerciseDTO(UUID workoutId, AddExerciseWebVo webVo) {
        if (webVo == null) throw new IllegalArgumentException("Missing request body");
        return new AddExerciseRequestDTO(
                workoutId,
                webVo.getCatalogId(),
                webVo.getName(),
                webVo.getNotes(),
                webVo.getPosition()
        );
    }
}
