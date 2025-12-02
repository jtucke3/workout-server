package com.jtucke3.workoutapi.webVo.workout.workout;

import java.time.LocalDateTime;

public class CreateWorkoutRequestWebVo {
    private String title;
    private LocalDateTime workoutAt;
    private String notes;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public LocalDateTime getWorkoutAt() { return workoutAt; }
    public void setWorkoutAt(LocalDateTime workoutAt) { this.workoutAt = workoutAt; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
