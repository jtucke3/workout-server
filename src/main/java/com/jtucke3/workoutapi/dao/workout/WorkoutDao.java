package com.jtucke3.workoutapi.dao.workout;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jtucke3.workoutapi.domain.entity.UserEntity;
import com.jtucke3.workoutapi.domain.entity.WorkoutEntity;
import com.jtucke3.workoutapi.domain.entity.WorkoutExerciseEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class WorkoutDao implements IWorkoutDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public WorkoutEntity createWorkout(UUID userId, String title, LocalDateTime workoutAt, String notes) {
        var user = em.find(UserEntity.class, userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + userId);
        }

        var w = new WorkoutEntity();
        w.setUser(user);
        w.setTitle(title);
        w.setWorkoutAt(workoutAt);
        w.setNotes(notes);
        w.setStatus(WorkoutEntity.Status.IN_PROGRESS);

        em.persist(w);
        return w;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<WorkoutEntity> findWorkoutById(UUID workoutId) {
        return Optional.ofNullable(em.find(WorkoutEntity.class, workoutId));
    }

    @Transactional
    @Override
    public WorkoutExerciseEntity addExercise(WorkoutEntity workout,
                                             String name,
                                             String notes,
                                             String bodyPart,
                                             String equipment) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Exercise name is required");
        }

        var ex = new WorkoutExerciseEntity();
        ex.setWorkout(workout);
        ex.setName(name);
        ex.setNotes(notes);
        ex.setBodyPart(bodyPart);
        ex.setEquipment(equipment);

        em.persist(ex);
        return ex;
    }

    @Transactional(readOnly = true)
    @Override
    public List<WorkoutExerciseEntity> getExercises(UUID workoutId) {
        return em.createQuery(
                "SELECT e FROM WorkoutExerciseEntity e WHERE e.workout.id = :workoutId",
                WorkoutExerciseEntity.class)
                .setParameter("workoutId", workoutId)
                .getResultList();
    }

    @Transactional
    @Override
    public void removeExercise(UUID exerciseId) {
        var ex = em.find(WorkoutExerciseEntity.class, exerciseId);
        if (ex == null) {
            throw new IllegalArgumentException("Exercise not found: " + exerciseId);
        }
        em.remove(ex);
    }

    // --- New method: get all workouts for a user ---
    @Transactional(readOnly = true)
    @Override
    public List<WorkoutEntity> findWorkoutsByUserId(UUID userId) {
        return em.createQuery(
                "SELECT w FROM WorkoutEntity w WHERE w.user.id = :userId ORDER BY w.workoutAt DESC",
                WorkoutEntity.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Transactional
    @Override
    public WorkoutEntity updateWorkout(UUID workoutId,
                                       String title,
                                       LocalDateTime workoutAt,
                                       String notes) {
        var workout = em.find(WorkoutEntity.class, workoutId);
        if (workout == null) {
            throw new IllegalArgumentException("Workout not found: " + workoutId);
        }

        // Update mutable fields
        if (title != null && !title.isBlank()) {
            workout.setTitle(title);
        }
        if (workoutAt != null) {
            workout.setWorkoutAt(workoutAt);
        }
        if (notes != null) {
            workout.setNotes(notes);
        }

        // Merge ensures changes are persisted
        return em.merge(workout);
    }
}
