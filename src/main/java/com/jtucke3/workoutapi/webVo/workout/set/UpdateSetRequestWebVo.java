package com.jtucke3.workoutapi.webVo.workout.set;

import java.util.UUID;

public class UpdateSetRequestWebVo {
    private UUID setId;
    private Integer reps;
    private Double weight;

    // --- Getters & Setters ---
    public UUID getSetId() {
        return setId;
    }

    public void setSetId(UUID setId) {
        this.setId = setId;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
