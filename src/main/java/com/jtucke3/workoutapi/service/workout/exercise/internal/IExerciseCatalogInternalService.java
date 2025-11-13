package com.jtucke3.workoutapi.service.workout.exercise.internal;

import java.util.List;

import com.jtucke3.workoutapi.dto.workout.exercise.ExerciseCatalogItemDTO;

public interface IExerciseCatalogInternalService {
    List<ExerciseCatalogItemDTO> search(String q, int limit);
}
