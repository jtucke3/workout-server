package com.jtucke3.workoutapi.dto.workout.workout;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.jtucke3.workoutapi.domain.entity.WorkoutEntity.Status;
import com.jtucke3.workoutapi.dto.workout.exercise.ExerciseResponseDTO;

/**
 * Response DTO for WorkoutEntity.
 * Represents a workout with its metadata and exercises.
 */
public record WorkoutResponseDTO(
        UUID id,
        String title,
        String notes,
        LocalDateTime workoutAt,
        Status status,
        Instant createdAt,
        List<ExerciseResponseDTO> exercises
) {}
