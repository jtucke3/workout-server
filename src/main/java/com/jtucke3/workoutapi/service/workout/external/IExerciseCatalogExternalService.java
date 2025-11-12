package com.jtucke3.workoutapi.service.workout.external;

import java.util.List;

import com.jtucke3.workoutapi.dto.workout.excercise.ExerciseCatalogItemDTO;

public interface IExerciseCatalogExternalService {
    List<ExerciseCatalogItemDTO> search(String q, int limit);
}
