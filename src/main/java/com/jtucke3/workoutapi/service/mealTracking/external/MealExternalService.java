package com.jtucke3.workoutapi.service.mealTracking.external;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jtucke3.workoutapi.dto.mealTracking.CreateMealRequestDTO;
import com.jtucke3.workoutapi.dto.mealTracking.MealResponseDTO;
import com.jtucke3.workoutapi.dto.mealTracking.UpdateMealRequestDTO;
import com.jtucke3.workoutapi.service.mealTracking.internal.IMealInternalService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MealExternalService implements IMealExternalService {

    private final IMealInternalService internal;

    @Transactional
    @Override
    public MealResponseDTO createMeal(CreateMealRequestDTO req) {
        return internal.createMeal(req);
    }

    @Transactional
    @Override
    public MealResponseDTO updateMeal(UpdateMealRequestDTO req) {
        return internal.updateMeal(req);
    }

    @Transactional(readOnly = true)
    @Override
    public MealResponseDTO getMealById(UUID mealId) {
        return internal.findById(mealId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<MealResponseDTO> getMealsByUser(UUID userId) {
        return internal.findByUserId(userId);
    }

    @Transactional
    @Override
    public void deleteMeal(UUID mealId) {
        internal.deleteMeal(mealId);
    }
}
