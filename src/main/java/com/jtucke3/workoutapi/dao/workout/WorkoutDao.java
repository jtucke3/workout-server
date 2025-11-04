package com.jtucke3.workoutapi.dao.workout;

import com.jtucke3.workoutapi.domain.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public class WorkoutDao implements IWorkoutDao {

    @PersistenceContext private EntityManager em;

    @Transactional
    @Override
    public WorkoutEntity createWorkout(UUID userId, String title, LocalDateTime workoutAt, String notes) {
        var user = em.find(UserEntity.class, userId);
        if (user == null) throw new IllegalArgumentException("User not found: " + userId);

        var w = new WorkoutEntity();
        w.setUser(user);
        w.setTitle(title);
        w.setWorkoutAt(workoutAt);
        w.setNotes(notes);
        w.setStatus(WorkoutEntity.Status.IN_PROGRESS);

        em.persist(w);
        em.flush();
        return w;
    }

    @Override
    public Optional<WorkoutEntity> findWorkoutById(UUID workoutId) {
        return Optional.ofNullable(em.find(WorkoutEntity.class, workoutId));
    }

    @Transactional
    @Override
    public WorkoutExerciseEntity addExercise(WorkoutEntity workout,
                                             String name,
                                             String notes,
                                             Integer position,
                                             UUID catalogId) {
        var ex = new WorkoutExerciseEntity();
        ex.setWorkout(workout);

        if (catalogId != null) {
            var cat = em.find(ExerciseCatalogEntity.class, catalogId);
            if (cat == null) throw new IllegalArgumentException("Catalog item not found: " + catalogId);
            ex.setCatalog(cat);
            ex.setName(cat.getName());
        } else {
            if (name == null || name.isBlank())
                throw new IllegalArgumentException("Name required when catalogId is null");
            ex.setName(name);
        }

        ex.setNotes(notes);
        ex.setPosition(position != null ? position : 1);
        em.persist(ex);
        em.flush();
        return ex;
    }
}
