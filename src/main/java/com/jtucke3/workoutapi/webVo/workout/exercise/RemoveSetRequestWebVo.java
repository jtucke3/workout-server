package com.jtucke3.workoutapi.webVo.workout.exercise;

import java.util.UUID;

public class RemoveSetRequestWebVo {
    private UUID setId;   // unique identifier of the set to remove

    public UUID getSetId() { return setId; }
    public void setSetId(UUID setId) { this.setId = setId; }
}
