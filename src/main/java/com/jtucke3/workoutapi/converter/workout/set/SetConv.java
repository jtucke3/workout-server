package com.jtucke3.workoutapi.converter.workout.set;

import com.jtucke3.workoutapi.dto.workout.set.SetResponseDTO;
import com.jtucke3.workoutapi.dto.workout.set.UpdateSetRequestDTO;
import com.jtucke3.workoutapi.webVo.workout.set.SetResponseWebVo;
import com.jtucke3.workoutapi.webVo.workout.set.UpdateSetRequestWebVo;

public class SetConv {

    // --- WebVo → DTO ---
    public static UpdateSetRequestDTO toDto(UpdateSetRequestWebVo webVo) {
        if (webVo == null) throw new IllegalArgumentException("Missing request body");
        return new UpdateSetRequestDTO(
                webVo.getSetId(),
                webVo.getReps(),
                webVo.getWeight()
        );
    }

    // --- DTO → WebVo ---
    public static SetResponseWebVo toWebVo(SetResponseDTO dto) {
        if (dto == null) throw new IllegalArgumentException("Missing response DTO");
        return new SetResponseWebVo(
                dto.setId(),
                dto.weight(),
                dto.reps(),
                dto.createdAt()
        );
    }
}
