package com.jtucke3.workoutapi.dao.workout;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.jtucke3.workoutapi.domain.entity.WorkoutExerciseEntity;
import com.jtucke3.workoutapi.domain.entity.WorkoutSetEntity;

public interface IWorkoutExerciseDao {
    Optional<WorkoutExerciseEntity> findById(UUID exerciseId);
    List<WorkoutExerciseEntity> findByWorkoutId(UUID workoutId);

    WorkoutSetEntity addSet(WorkoutExerciseEntity excercise);
    void removeSet(UUID setId);
}
