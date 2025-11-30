package com.jtucke3.workoutapi.dao.mealTracking;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jtucke3.workoutapi.domain.entity.UserEntity;
import com.jtucke3.workoutapi.domain.entity.mealTracking.MealEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class MealDao implements IMealDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<MealEntity> findById(UUID mealId) {
        return Optional.ofNullable(em.find(MealEntity.class, mealId));
    }

    @Override
    public List<MealEntity> findByUserId(UUID userId) {
        return em.createQuery(
                "SELECT m FROM MealEntity m WHERE m.user.id = :userId",
                MealEntity.class
        ).setParameter("userId", userId)
         .getResultList();
    }

    @Transactional
    @Override
    public MealEntity saveMeal(MealEntity meal) {
        if (meal.getId() == null) {
            em.persist(meal);
        } else {
            meal = em.merge(meal);
        }
        em.flush();
        return meal;
    }

    @Transactional
    @Override
    public MealEntity createMeal(UUID userId, String name, int calories, Instant mealAtUtc, String notes) {
        var user = em.find(UserEntity.class, userId);
        if (user == null) throw new IllegalArgumentException("User not found: " + userId);

        var meal = new MealEntity();
        meal.setUser(user);
        meal.setName(name);
        meal.setCalories(calories);
        meal.setMealAtUtc(mealAtUtc);   // ✅ fixed field name
        meal.setNotes(notes);

        em.persist(meal);
        em.flush();
        return meal;
    }

    @Transactional
    @Override
    public void deleteMeal(UUID mealId) {
        var meal = em.find(MealEntity.class, mealId);
        if (meal == null) throw new IllegalArgumentException("Meal not found: " + mealId);
        em.remove(meal);                // ✅ hard delete
    }
}
