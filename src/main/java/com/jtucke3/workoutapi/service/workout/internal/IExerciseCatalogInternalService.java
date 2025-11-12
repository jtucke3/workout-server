package com.jtucke3.workoutapi.service.workout.internal;

import java.util.List;

import com.jtucke3.workoutapi.dto.workout.excercise.ExerciseCatalogItemDTO;

public interface IExerciseCatalogInternalService {
    List<ExerciseCatalogItemDTO> search(String q, int limit);
}
