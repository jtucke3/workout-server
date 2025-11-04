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
    }a

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

    @Override
    public boolean existsByEmail(String email) {
        var cb = em.getCriteriaBuilder();
        var cq = cb.createQuery(Long.class);
        var root = cq.from(UserEntity.class);
        cq.select(cb.count(root))
                .where(cb.equal(cb.lower(root.get("email")), email.toLowerCase()));
        var cnt = em.createQuery(cq).getSingleResult();
        return cnt != null && cnt > 0;
    }

    @Override
    public UserDTO saveNew(String email, String displayName, String passwordHash) {
        var e = new UserEntity();
        e.setEmail(email.toLowerCase());
        e.setDisplayName(displayName);
        e.setPasswordHash(passwordHash);
        em.persist(e);
        em.flush();
        return conv.toDto(e);
    }

    @Override
    public Optional<String> findTwoFactorSecretByEmail(String email) {
        var cb = em.getCriteriaBuilder();
        var cq = cb.createQuery(String.class);
        var root = cq.from(UserEntity.class);
        cq.select(root.get("twoFactorSecret"))
                .where(cb.equal(cb.lower(root.get("email")), email.toLowerCase()));
        var list = em.createQuery(cq).setMaxResults(1).getResultList();
        return list.isEmpty() ? Optional.empty() : Optional.ofNullable(list.get(0));
    }

    @Override
    public void storeTwoFactorSecret(String email, String secret, boolean enabled) {
        var opt = findEntityByEmail(email);
        if (opt.isEmpty()) throw new IllegalArgumentException("User not found: " + email);
        var e = opt.get();
        e.setTwoFactorSecret(secret);
        e.setTwoFactorEnabled(enabled);
        em.merge(e);
    }

    @Override
    public Optional<UserEntity> findEntityById(UUID id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(em.find(UserEntity.class, id));
    }

    @Override
    public UserEntity saveEntity(UserEntity entity) {
        if (entity == null) throw new IllegalArgumentException("entity cannot be null");
        if (entity.getId() == null) {
            em.persist(entity);
            em.flush();
            return entity;
        } else {
            return em.merge(entity);
        }
    }

}
