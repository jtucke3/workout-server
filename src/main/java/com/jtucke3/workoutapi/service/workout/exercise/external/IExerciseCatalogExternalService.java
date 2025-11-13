package com.jtucke3.workoutapi.service.workout.exercise.external;

import java.util.List;

import com.jtucke3.workoutapi.dto.workout.exercise.ExerciseCatalogItemDTO;

public interface IExerciseCatalogExternalService {
    List<ExerciseCatalogItemDTO> search(String q, int limit);
}
