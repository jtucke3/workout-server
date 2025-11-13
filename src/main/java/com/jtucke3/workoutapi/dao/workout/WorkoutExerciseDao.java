package com.jtucke3.workoutapi.dao.workout;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jtucke3.workoutapi.domain.entity.WorkoutExerciseEntity;
import com.jtucke3.workoutapi.domain.entity.WorkoutSetEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class WorkoutExerciseDao implements IWorkoutExerciseDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<WorkoutExerciseEntity> findById(UUID exerciseId) {
        return Optional.ofNullable(em.find(WorkoutExerciseEntity.class, exerciseId));
    }

    @Override
    public List<WorkoutExerciseEntity> findByWorkoutId(UUID workoutId) {
        return em.createQuery("""
            SELECT e FROM WorkoutExerciseEntity e
            WHERE e.workout.id = :workoutId
            ORDER BY e.position ASC
        """, WorkoutExerciseEntity.class)
        .setParameter("workoutId", workoutId)
        .getResultList();
    }

    @Transactional
    @Override
    public WorkoutSetEntity addSet(UUID exerciseId) {
        var exercise = em.find(WorkoutExerciseEntity.class, exerciseId);
        if (exercise == null) {
            throw new IllegalArgumentException("Exercise not found: " + exerciseId);
        }

        var set = new WorkoutSetEntity();
        set.setExercise(exercise);
        set.setWeight(0.0);
        set.setReps(0);
        set.setCreatedAt(Instant.now());

        em.persist(set);
        em.flush();
        return set;
    }

    @Transactional
    @Override
    public void removeSet(UUID setId) {
        var set = em.find(WorkoutSetEntity.class, setId);
        if (set == null) {
            throw new IllegalArgumentException("Set not found: " + setId);
        }

        em.remove(set);
    }
}
