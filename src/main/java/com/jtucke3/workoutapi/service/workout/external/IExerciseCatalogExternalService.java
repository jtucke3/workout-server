package com.jtucke3.workoutapi.service.workout.external;

import com.jtucke3.workoutapi.dto.workout.ExerciseCatalogItemDTO;

import java.util.List;

public interface IExerciseCatalogExternalService {
    List<ExerciseCatalogItemDTO> search(String q, int limit);
}
