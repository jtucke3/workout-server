package com.jtucke3.workoutapi.service.mealTracking.internal;

import java.util.List;
import java.util.UUID;

import com.jtucke3.workoutapi.dto.mealTracking.CreateMealRequestDTO;
import com.jtucke3.workoutapi.dto.mealTracking.MealResponseDTO;
import com.jtucke3.workoutapi.dto.mealTracking.UpdateMealRequestDTO;

public interface IMealInternalService {

    MealResponseDTO createMeal(CreateMealRequestDTO req);

    MealResponseDTO updateMeal(UpdateMealRequestDTO req);

    MealResponseDTO findById(UUID mealId);

    List<MealResponseDTO> findByUserId(UUID userId);

    void deleteMeal(UUID mealId);
}
