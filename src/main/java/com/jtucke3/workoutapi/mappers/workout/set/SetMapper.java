package com.jtucke3.workoutapi.mappers.workout.set;

import com.jtucke3.workoutapi.domain.entity.WorkoutSetEntity;
import com.jtucke3.workoutapi.dto.workout.set.SetResponseDTO;

public class SetMapper {

    public static SetResponseDTO toDTO(WorkoutSetEntity set) {
        return new SetResponseDTO(
            set.getId(),
            set.getWeight(),
            set.getReps(),
            set.getCreatedAt()
        );
    }
}
