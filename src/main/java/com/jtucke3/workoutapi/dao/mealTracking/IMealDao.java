package com.jtucke3.workoutapi.dao.mealTracking;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.jtucke3.workoutapi.domain.entity.mealTracking.MealEntity;

public interface IMealDao {

    // --- Queries ---
    Optional<MealEntity> findById(UUID mealId);

    List<MealEntity> findByUserId(UUID userId);

    // --- Persistence ---
    MealEntity saveMeal(MealEntity meal);   // covers both create + update

    void deleteMeal(UUID mealId);           // hard delete by ID
}
