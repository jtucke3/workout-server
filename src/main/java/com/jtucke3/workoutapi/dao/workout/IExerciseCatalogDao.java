package com.jtucke3.workoutapi.dao.workout;

import com.jtucke3.workoutapi.domain.entity.ExerciseCatalogEntity;

import java.util.List;

public interface IExerciseCatalogDao {
    List<ExerciseCatalogEntity> search(String q, int limit);
}
