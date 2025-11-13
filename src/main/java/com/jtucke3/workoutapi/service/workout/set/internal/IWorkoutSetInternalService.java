package com.jtucke3.workoutapi.service.workout.set.internal;

import com.jtucke3.workoutapi.dto.workout.set.SetRepsRequestDTO;
import com.jtucke3.workoutapi.dto.workout.set.SetRepsResponseDTO;
import com.jtucke3.workoutapi.dto.workout.set.SetWeightRequestDTO;
import com.jtucke3.workoutapi.dto.workout.set.SetWeightResponseDTO;

public interface IWorkoutSetInternalService {
    SetWeightResponseDTO setWeight(SetWeightRequestDTO req);
    SetRepsResponseDTO setReps(SetRepsRequestDTO req);
}
