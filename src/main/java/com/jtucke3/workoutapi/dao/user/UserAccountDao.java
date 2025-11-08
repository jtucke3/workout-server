package com.jtucke3.workoutapi.dao.user;

import com.jtucke3.workoutapi.domain.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserAccountDao implements IUserAccountDao {

    @PersistenceContext private EntityManager em;

    @Override
    public Optional<UserEntity> findById(UUID userId) {
        UserEntity user = em.find(UserEntity.class, userId);
        return Optional.ofNullable(user);
    }

    @Override
    public void save(UserEntity user) {
        if (user.getId() == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }
    }
}
