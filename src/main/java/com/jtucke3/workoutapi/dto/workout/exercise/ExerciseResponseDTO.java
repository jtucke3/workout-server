package com.jtucke3.workoutapi.dto.workout.exercise;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.jtucke3.workoutapi.dto.workout.set.SetResponseDTO;

/**
 * Response DTO for WorkoutExerciseEntity.
 * Represents an exercise within a workout, including its sets.
 */
public record ExerciseResponseDTO(
        UUID id,
        String name,
        String notes,
        String equipment,
        String bodyPart,
        Instant createdAt,
        List<SetResponseDTO> sets
) {}
