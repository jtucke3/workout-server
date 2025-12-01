package com.jtucke3.workoutapi.service.workout.set.internal;

import com.jtucke3.workoutapi.dto.workout.set.SetResponseDTO;
import com.jtucke3.workoutapi.dto.workout.set.UpdateSetRequestDTO;

public interface IWorkoutSetInternalService {

    /**
     * Update a workout set's reps and/or weight.
     *
     * @param req request containing setId, reps, and weight
     * @return response containing updated set details
     */
    SetResponseDTO updateSet(UpdateSetRequestDTO req);
}
