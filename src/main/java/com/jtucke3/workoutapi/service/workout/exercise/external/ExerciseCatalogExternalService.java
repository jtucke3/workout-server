package com.jtucke3.workoutapi.service.workout.exercise.external;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jtucke3.workoutapi.dto.workout.exercise.ExerciseCatalogItemDTO;
import com.jtucke3.workoutapi.service.workout.exercise.internal.IExerciseCatalogInternalService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExerciseCatalogExternalService implements IExerciseCatalogExternalService {

    private final IExerciseCatalogInternalService internal;

    @Override
    public List<ExerciseCatalogItemDTO> search(String q, int limit) {
        return internal.search(q, limit <= 0 ? 50 : Math.min(limit, 100));
    }
}
