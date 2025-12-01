package com.jtucke3.workoutapi.webVo.workout.workout;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.jtucke3.workoutapi.webVo.workout.exercise.ExerciseResponseWebVo;

/**
 * Web VO for returning workout details to the client.
 * Mirrors WorkoutEntity fields and includes exercises.
 */
public class WorkoutResponseWebVo {
    private UUID id;
    private String title;
    private String notes;
    private LocalDateTime workoutAt;
    private Instant createdAt;
    private List<ExerciseResponseWebVo> exercises;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public LocalDateTime getWorkoutAt() { return workoutAt; }
    public void setWorkoutAt(LocalDateTime workoutAt) { this.workoutAt = workoutAt; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public List<ExerciseResponseWebVo> getExercises() { return exercises; }
    public void setExercises(List<ExerciseResponseWebVo> exercises) { this.exercises = exercises; }
}
