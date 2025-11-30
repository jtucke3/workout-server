package com.jtucke3.workoutapi.service.mealTracking.external;

import java.util.List;
import java.util.UUID;

import com.jtucke3.workoutapi.dto.mealTracking.CreateMealRequestDTO;
import com.jtucke3.workoutapi.dto.mealTracking.MealResponseDTO;
import com.jtucke3.workoutapi.dto.mealTracking.UpdateMealRequestDTO;

public interface IMealExternalService {

    // --- Public API operations ---
    MealResponseDTO createMeal(CreateMealRequestDTO req);

    MealResponseDTO updateMeal(UpdateMealRequestDTO req);

    MealResponseDTO getMealById(UUID mealId);

    List<MealResponseDTO> getMealsByUser(UUID userId);

    void deleteMeal(UUID mealId);
}
