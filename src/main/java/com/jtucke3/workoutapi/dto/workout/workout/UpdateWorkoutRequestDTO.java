package com.jtucke3.workoutapi.dto.workout.workout;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for updating an existing workout.
 * Contains the workoutId plus the mutable fields.
 */
public class UpdateWorkoutRequestDTO {
    private UUID workoutId;
    private String title;
    private LocalDateTime workoutAt;
    private String notes;

    public UpdateWorkoutRequestDTO() {}

    public UpdateWorkoutRequestDTO(UUID workoutId, String title, LocalDateTime workoutAt, String notes) {
        this.workoutId = workoutId;
        this.title = title;
        this.workoutAt = workoutAt;
        this.notes = notes;
    }

    public UUID getWorkoutId() { return workoutId; }
    public void setWorkoutId(UUID workoutId) { this.workoutId = workoutId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public LocalDateTime getWorkoutAt() { return workoutAt; }
    public void setWorkoutAt(LocalDateTime workoutAt) { this.workoutAt = workoutAt; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
