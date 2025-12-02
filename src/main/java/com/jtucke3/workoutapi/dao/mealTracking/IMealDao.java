package com.jtucke3.workoutapi.dao.mealTracking;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.jtucke3.workoutapi.domain.entity.mealTracking.MealEntity;

public interface IMealDao {

    // --- Queries ---
    Optional<MealEntity> findById(UUID mealId);

    List<MealEntity> findByUserEmail(String userEmail);

    // --- Persistence ---
    MealEntity saveMeal(MealEntity meal);  // update existing or persist new

    MealEntity createMeal(String userEmail, String name, int calories, Instant mealAtUtc, String notes);

    void deleteMeal(UUID mealId);          // hard delete by ID
}
