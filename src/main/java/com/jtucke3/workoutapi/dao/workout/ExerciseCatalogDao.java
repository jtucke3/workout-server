package com.jtucke3.workoutapi.dao.workout;

import com.jtucke3.workoutapi.domain.entity.ExerciseCatalogEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExerciseCatalogDao implements IExerciseCatalogDao {

    @PersistenceContext private EntityManager em;

    @Override
    public List<ExerciseCatalogEntity> search(String q, int limit) {
        var cb = em.getCriteriaBuilder();
        var cq = cb.createQuery(ExerciseCatalogEntity.class);
        var root = cq.from(ExerciseCatalogEntity.class);

        if (q == null || q.isBlank()) {
            cq.select(root).orderBy(cb.asc(root.get("name")));
            return em.createQuery(cq).setMaxResults(limit).getResultList();
        }
        var like = "%" + q.toLowerCase() + "%";
        cq.select(root).where(cb.like(cb.lower(root.get("name")), like))
                .orderBy(cb.asc(root.get("name")));
        return em.createQuery(cq).setMaxResults(limit).getResultList();
    }
}
