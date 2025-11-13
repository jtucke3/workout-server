package com.jtucke3.workoutapi.service.workout.exercise.internal;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jtucke3.workoutapi.dao.workout.IExerciseCatalogDao;
import com.jtucke3.workoutapi.dto.workout.exercise.ExerciseCatalogItemDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExerciseCatalogInternalService implements IExerciseCatalogInternalService {

    private final IExerciseCatalogDao dao;

    @Override
    public List<ExerciseCatalogItemDTO> search(String q, int limit) {
        return dao.search(q, limit).stream()
                .map(e -> new ExerciseCatalogItemDTO(e.getId(), e.getName(), e.getBodyPart(), e.getEquipment()))
                .toList();
    }
}
