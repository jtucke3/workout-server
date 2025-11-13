package com.jtucke3.workoutapi.dao.workout;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.jtucke3.workoutapi.domain.entity.WorkoutSetEntity;

public interface IWorkoutSetDao {
    Optional<WorkoutSetEntity> findById(UUID setId);
    List<WorkoutSetEntity> findByExerciseId(UUID exerciseId);

    WorkoutSetEntity updateWeight(UUID setId, Double weight);
    WorkoutSetEntity updateReps(UUID setId, Integer reps);
}
