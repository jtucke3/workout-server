package com.jtucke3.workoutapi.converter.workout.set;

import java.util.UUID;

import com.jtucke3.workoutapi.dto.workout.set.SetRepsRequestDTO;
import com.jtucke3.workoutapi.dto.workout.set.SetWeightRequestDTO;
import com.jtucke3.workoutapi.webVo.workout.set.SetRepsWebVo;
import com.jtucke3.workoutapi.webVo.workout.set.SetWeightWebVo;

public class SetConv {

    public static SetRepsRequestDTO toSetRepsDTO(UUID setId, SetRepsWebVo webVo) {
        if (webVo == null) throw new IllegalArgumentException("Missing request body");
        return new SetRepsRequestDTO(
                setId,
				webVo.getReps()
        );
    }

    public static SetWeightRequestDTO toSetWeightDTO(UUID setId, SetWeightWebVo webVo) {
        if (webVo == null) throw new IllegalArgumentException("Missing request body");
        return new SetWeightRequestDTO(
			setId,
            webVo.getWeight()
        );
    }
}
