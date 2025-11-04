package com.jtucke3.workoutapi.dao.workout;

import com.jtucke3.workoutapi.domain.entity.WorkoutEntity;
import com.jtucke3.workoutapi.domain.entity.WorkoutExerciseEntity;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface IWorkoutDao {
    WorkoutEntity createWorkout(UUID userId, String title, LocalDateTime workoutAt, String notes);
    Optional<WorkoutEntity> findWorkoutById(UUID workoutId);

    WorkoutExerciseEntity addExercise(WorkoutEntity workout,
                                      String name,
                                      String notes,
                                      Integer position,
                                      UUID catalogId);
}
