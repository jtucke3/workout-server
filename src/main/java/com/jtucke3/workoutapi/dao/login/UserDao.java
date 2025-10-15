package com.jtucke3.workoutapi.dao.login;

import com.jtucke3.workoutapi.converter.user.UserConv;
import com.jtucke3.workoutapi.domain.entity.UserEntity;
import com.jtucke3.workoutapi.dto.user.UserDTO;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserDao implements IUserDao {

    @PersistenceContext private EntityManager em;
    private final UserConv conv;

    public UserDao(UserConv conv) { this.conv = conv; }

    @Override
    public Optional<UserDTO> findPublicByEmail(String email) {
        return findEntityByEmail(email).map(conv::toDto);
    }

    @Override
    public Optional<String> findPasswordHashByEmail(String email) {
        var cb = em.getCriteriaBuilder();
        var cq = cb.createQuery(String.class);
        var root = cq.from(UserEntity.class);
        cq.select(root.get("passwordHash"))
                .where(cb.equal(cb.lower(root.get("email")), email.toLowerCase()));
        var list = em.createQuery(cq).setMaxResults(1).getResultList();
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    private Optional<UserEntity> findEntityByEmail(String email) {
        var cb = em.getCriteriaBuilder();
        var cq = cb.createQuery(UserEntity.class);
        var root = cq.from(UserEntity.class);
        cq.select(root).where(cb.equal(cb.lower(root.get("email")), email.toLowerCase()));
        var list = em.createQuery(cq).setMaxResults(1).getResultList();
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }
}
