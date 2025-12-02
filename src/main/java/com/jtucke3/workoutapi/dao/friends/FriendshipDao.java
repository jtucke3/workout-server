package com.jtucke3.workoutapi.dao.friends;

import com.jtucke3.workoutapi.domain.entity.FriendshipEntity;
import com.jtucke3.workoutapi.domain.entity.UserEntity;
import com.jtucke3.workoutapi.domain.enums.FriendshipStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class FriendshipDao implements IFriendshipDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<FriendshipEntity> findByUsers(UUID userId, UUID friendId) {
        TypedQuery<FriendshipEntity> q = em.createQuery(
                "select f from FriendshipEntity f " +
                        "where f.user.id = :userId and f.friend.id = :friendId",
                FriendshipEntity.class
        );
        q.setParameter("userId", userId);
        q.setParameter("friendId", friendId);
        List<FriendshipEntity> list = q.setMaxResults(1).getResultList();
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    @Override
    public Optional<FriendshipEntity> findPendingRequest(UUID fromUserId, UUID toUserId) {
        TypedQuery<FriendshipEntity> q = em.createQuery(
                "select f from FriendshipEntity f " +
                        "where f.user.id = :fromId and f.friend.id = :toId " +
                        "and f.status = :status",
                FriendshipEntity.class
        );
        q.setParameter("fromId", fromUserId);
        q.setParameter("toId", toUserId);
        q.setParameter("status", FriendshipStatus.PENDING);

        List<FriendshipEntity> list = q.setMaxResults(1).getResultList();
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    @Override
    public List<UserEntity> findAcceptedFriends(UUID userId) {
        TypedQuery<UserEntity> q = em.createQuery(
                "select f.friend from FriendshipEntity f " +
                        "where f.user.id = :userId and f.status = :status " +
                        "order by f.friend.displayName asc",
                UserEntity.class
        );
        q.setParameter("userId", userId);
        q.setParameter("status", FriendshipStatus.ACCEPTED);
        return q.getResultList();
    }

    @Override
    public FriendshipEntity save(FriendshipEntity entity) {
        if (entity.getId() == null) {
            em.persist(entity);
            return entity;
        } else {
            return em.merge(entity);
        }
    }

    @Override
    public void deleteBetween(UUID userId, UUID friendId) {
        em.createQuery(
                        "delete from FriendshipEntity f " +
                                "where (f.user.id = :u1 and f.friend.id = :u2) " +
                                "   or (f.user.id = :u2 and f.friend.id = :u1)"
                )
                .setParameter("u1", userId)
                .setParameter("u2", friendId)
                .executeUpdate();
    }

    @Override
    public List<UserEntity> searchUsers(String query, UUID excludeUserId) {
        String like = "%" + query.toLowerCase() + "%";

        TypedQuery<UserEntity> q = em.createQuery(
                "select u from UserEntity u " +
                        "where u.id <> :current " +
                        "and (" +
                        "  lower(u.displayName) like :q " +
                        "  or lower(u.email) like :q " +
                        "  or lower(u.username) like :q" +
                        ") " +
                        "order by u.displayName asc",
                UserEntity.class
        );
        q.setParameter("current", excludeUserId);
        q.setParameter("q", like);
        return q.getResultList();
    }
}
