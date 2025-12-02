package com.jtucke3.workoutapi.dao.workout;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.jtucke3.workoutapi.domain.entity.WorkoutEntity;
import com.jtucke3.workoutapi.domain.entity.WorkoutExerciseEntity;

public interface IWorkoutDao {

    WorkoutEntity createWorkout(UUID userId,
                                String title,
                                LocalDateTime workoutAt,
                                String notes);

    void removeWorkout(UUID workoutId);


    Optional<WorkoutEntity> findWorkoutById(UUID workoutId);

    WorkoutExerciseEntity addExercise(WorkoutEntity workout,
                                      String name,
                                      String notes,
                                      String bodyPart,
                                      String equipment);

    List<WorkoutExerciseEntity> getExercises(UUID workoutId);

    void removeExercise(UUID exerciseId);

    // --- New method: get all workouts for a user ---
    List<WorkoutEntity> findWorkoutsByUserId(UUID userId);

    // --- New method: update an existing workout ---
    WorkoutEntity updateWorkout(UUID workoutId,
                                String title,
                                LocalDateTime workoutAt,
                                String notes);
}
