package com.jtucke3.workoutapi.converter.workout.exercise;

import java.util.UUID;
import java.util.stream.Collectors;

import com.jtucke3.workoutapi.converter.workout.set.SetConv;
import com.jtucke3.workoutapi.dto.workout.exercise.AddSetRequestDTO;
import com.jtucke3.workoutapi.dto.workout.exercise.ExerciseResponseDTO;
import com.jtucke3.workoutapi.dto.workout.exercise.RemoveSetRequestDTO;
import com.jtucke3.workoutapi.webVo.workout.exercise.AddSetRequestWebVo;
import com.jtucke3.workoutapi.webVo.workout.exercise.ExerciseResponseWebVo;
import com.jtucke3.workoutapi.webVo.workout.exercise.RemoveSetRequestWebVo;

public class ExerciseConv {

    public static AddSetRequestDTO toAddSetDTO(UUID excerciseId, AddSetRequestWebVo webVo) {
        if (webVo == null) {
            throw new IllegalArgumentException("Missing request body");
        }
        return new AddSetRequestDTO(
                excerciseId
        );
    }

    public static RemoveSetRequestDTO toRemoveSetDTO(RemoveSetRequestWebVo webVo) {
        if (webVo == null) {
            throw new IllegalArgumentException("Missing request body");
        }
        if (webVo.getSetId() == null) {
            throw new IllegalArgumentException("Missing setId");
        }
        return new RemoveSetRequestDTO(
                webVo.getSetId()
        );
    }

    public static ExerciseResponseWebVo toResponseWebVo(ExerciseResponseDTO dto) {
        if (dto == null) {
            return null;
        }

        var webVo = new ExerciseResponseWebVo();
        webVo.setId(dto.id());
        webVo.setName(dto.name());
        webVo.setNotes(dto.notes());
        webVo.setEquipment(dto.equipment());
        webVo.setBodyPart(dto.bodyPart());
        webVo.setCreatedAt(dto.createdAt());

        if (dto.sets() != null) {
            webVo.setSets(
                    dto.sets().stream()
                            .map(SetConv::toWebVo)
                            .collect(Collectors.toList())
            );
        }

        return webVo;
    }
}
