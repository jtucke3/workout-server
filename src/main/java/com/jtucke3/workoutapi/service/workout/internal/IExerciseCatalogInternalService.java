package com.jtucke3.workoutapi.service.workout.internal;

import com.jtucke3.workoutapi.dto.workout.ExerciseCatalogItemDTO;

import java.util.List;

public interface IExerciseCatalogInternalService {
    List<ExerciseCatalogItemDTO> search(String q, int limit);
}
