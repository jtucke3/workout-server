package com.jtucke3.workoutapi.dao.friends;

import com.jtucke3.workoutapi.domain.entity.FriendshipEntity;
import com.jtucke3.workoutapi.domain.entity.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IFriendshipDao {

    /**
     * Find an existing friendship row for this (user, friend) pair, if any.
     */
    Optional<FriendshipEntity> findByUsers(UUID userId, UUID friendId);

    /**
     * Find a pending request where fromUser -> toUser.
     */
    Optional<FriendshipEntity> findPendingRequest(UUID fromUserId, UUID toUserId);

    /**
     * Return all "friend" users for whom (user -> friend) is in ACCEPTED state.
     */
    List<UserEntity> findAcceptedFriends(UUID userId);

    /**
     * Persist or merge a friendship entity.
     */
    FriendshipEntity save(FriendshipEntity entity);

    /**
     * Remove all friendship rows between the two users (both directions).
     */
    void deleteBetween(UUID userId, UUID friendId);

    /**
     * Simple search over users excluding the current user.
     * NOTE: this does not filter out existing friends yet – the service can
     *       choose how to represent those.
     */
    List<UserEntity> searchUsers(String query, UUID excludeUserId);
}
