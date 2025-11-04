package com.jtucke3.workoutapi.service.workout.external;

import com.jtucke3.workoutapi.dto.workout.ExerciseCatalogItemDTO;
import com.jtucke3.workoutapi.service.workout.internal.IExerciseCatalogInternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseCatalogExternalService implements IExerciseCatalogExternalService {

    private final IExerciseCatalogInternalService internal;

    @Override
    public List<ExerciseCatalogItemDTO> search(String q, int limit) {
        return internal.search(q, limit <= 0 ? 50 : Math.min(limit, 100));
    }
}
