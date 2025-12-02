package com.jtucke3.workoutapi.service.mealTracking.internal;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jtucke3.workoutapi.dao.mealTracking.IMealDao;
import com.jtucke3.workoutapi.domain.entity.mealTracking.MealEntity;
import com.jtucke3.workoutapi.dto.mealTracking.CreateMealRequestDTO;
import com.jtucke3.workoutapi.dto.mealTracking.MealResponseDTO;
import com.jtucke3.workoutapi.dto.mealTracking.UpdateMealRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MealInternalService implements IMealInternalService {

    private final IMealDao mealDao;

    @Transactional
    @Override
    public MealResponseDTO createMeal(CreateMealRequestDTO req) {
        var meal = mealDao.createMeal(
                req.userEmail(),
                req.name(),
                req.calories(),
                req.mealAtUtc(),
                req.notes()
        );
        return toResponse(meal);
    }

    @Transactional
    @Override
    public MealResponseDTO updateMeal(UpdateMealRequestDTO req) {
        var meal = mealDao.findById(req.mealId())
                .orElseThrow(() -> new IllegalArgumentException("Meal not found: " + req.mealId()));

        if (req.name() != null) meal.setName(req.name());
        if (req.calories() != null) meal.setCalories(req.calories());
        if (req.mealAtUtc() != null) meal.setMealAtUtc(req.mealAtUtc());
        if (req.notes() != null) meal.setNotes(req.notes());

        meal = mealDao.saveMeal(meal);
        return toResponse(meal);
    }

    @Transactional(readOnly = true)
    @Override
    public MealResponseDTO findById(UUID mealId) {
        var meal = mealDao.findById(mealId)
                .orElseThrow(() -> new IllegalArgumentException("Meal not found: " + mealId));
        return toResponse(meal);
    }

    @Transactional(readOnly = true)
    @Override
    public List<MealResponseDTO> findByUserEmail(String userEmail) {
        return mealDao.findByUserEmail(userEmail).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    @Override
    public void deleteMeal(UUID mealId) {
        mealDao.deleteMeal(mealId); // delegate to DAO
    }

    // --- Mapping helper ---
    private MealResponseDTO toResponse(MealEntity meal) {
        return new MealResponseDTO(
                meal.getId(),
                meal.getUser().getEmail(),
                meal.getName(),
                meal.getCalories(),
                meal.getMealAtUtc(),
                meal.getNotes(),
                meal.getCreatedAt()
        );
    }
}
