package com.jtucke3.workoutapi.converter.workout.exercise;

import java.util.UUID;

import com.jtucke3.workoutapi.dto.workout.exercise.AddSetRequestDTO;
import com.jtucke3.workoutapi.dto.workout.exercise.RemoveSetRequestDTO;
import com.jtucke3.workoutapi.webVo.workout.exercise.AddSetWebVo;
import com.jtucke3.workoutapi.webVo.workout.exercise.RemoveSetWebVo;

public class ExerciseConv {

    public static AddSetRequestDTO toAddSetDTO(UUID excerciseId, AddSetWebVo webVo) {
        if (webVo == null) throw new IllegalArgumentException("Missing request body");
        return new AddSetRequestDTO(
                excerciseId
        );
    }

    public static RemoveSetRequestDTO toRemoveSetDTO(RemoveSetWebVo webVo) {
        if (webVo == null) throw new IllegalArgumentException("Missing request body");
        if (webVo.getSetId() == null) throw new IllegalArgumentException("Missing setId");
        return new RemoveSetRequestDTO(
                webVo.getSetId()
        );
    }
}
