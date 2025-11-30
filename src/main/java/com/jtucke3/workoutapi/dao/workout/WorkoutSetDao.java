package com.jtucke3.workoutapi.dao.workout;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jtucke3.workoutapi.domain.entity.WorkoutSetEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class WorkoutSetDao implements IWorkoutSetDao {

    @PersistenceContext 
    private EntityManager em;

    @Override
    public Optional<WorkoutSetEntity> findById(UUID setId) {
        return Optional.ofNullable(em.find(WorkoutSetEntity.class, setId));
    }

    @Override
    public List<WorkoutSetEntity> findByExerciseId(UUID exerciseId) {
        return em.createQuery("""
            SELECT s FROM WorkoutSetEntity s
            WHERE s.exercise.id = :exerciseId
            ORDER BY s.createdAt ASC
        """, WorkoutSetEntity.class)
        .setParameter("exerciseId", exerciseId)
        .getResultList();
    }

    @Transactional
    @Override
    public WorkoutSetEntity save(WorkoutSetEntity set) {
        if (set.getId() == null) {
            em.persist(set);
            return set;
        } else {
            return em.merge(set);
        }
    }

    @Transactional
    @Override
    public void deleteById(UUID setId) {
        WorkoutSetEntity set = em.find(WorkoutSetEntity.class, setId);
        if (set != null) {
            em.remove(set);
        }
    }
}
