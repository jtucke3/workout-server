package com.jtucke3.workoutapi.service.friends.internal;

import com.jtucke3.workoutapi.dao.friends.IFriendshipDao;
import com.jtucke3.workoutapi.dao.user.IUserAccountDao;
import com.jtucke3.workoutapi.domain.entity.FriendshipEntity;
import com.jtucke3.workoutapi.domain.entity.UserEntity;
import com.jtucke3.workoutapi.domain.enums.FriendshipStatus;
import com.jtucke3.workoutapi.dto.friends.FriendActivityDTO;
import com.jtucke3.workoutapi.dto.friends.FriendPreviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FriendInternalService implements IFriendInternalService {

    private final IFriendshipDao friendshipDao;
    private final IUserAccountDao userDao;

    @Override
    @Transactional(readOnly = true)
    public List<FriendPreviewDTO> searchUsers(String query, UUID currentUserId) {
        if (query == null || query.isBlank()) {
            return List.of();
        }

        List<UserEntity> users = friendshipDao.searchUsers(query.trim(), currentUserId);

        List<FriendPreviewDTO> results = new ArrayList<>();
        for (UserEntity u : users) {
            var friendship = friendshipDao.findByUsers(currentUserId, u.getId());
            boolean isFriend = friendship
                    .map(f -> f.getStatus() == FriendshipStatus.ACCEPTED)
                    .orElse(false);
            boolean isPending = friendship
                    .map(f -> f.getStatus() == FriendshipStatus.PENDING)
                    .orElse(false);

            results.add(new FriendPreviewDTO(
                    u.getId(),
                    u.getDisplayName(),
                    isFriend,
                    isPending
            ));
        }
        return results;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FriendPreviewDTO> listFriends(UUID currentUserId) {
        List<UserEntity> friends = friendshipDao.findAcceptedFriends(currentUserId);
        List<FriendPreviewDTO> results = new ArrayList<>(friends.size());

        for (UserEntity u : friends) {
            results.add(new FriendPreviewDTO(
                    u.getId(),
                    u.getDisplayName(),
                    true,
                    false
            ));
        }
        return results;
    }

    @Override
    @Transactional
    public void sendFriendRequest(UUID currentUserId, UUID targetUserId) {
        if (currentUserId.equals(targetUserId)) {
            throw new IllegalArgumentException("You cannot add yourself as a friend.");
        }

        userDao.findById(targetUserId)
                .orElseThrow(() -> new IllegalArgumentException("Target user not found"));

        var existing = friendshipDao.findByUsers(currentUserId, targetUserId);
        if (existing.isPresent()) {
            FriendshipStatus status = existing.get().getStatus();
            if (status == FriendshipStatus.ACCEPTED) {
                return; // already friends, nothing to do
            }
            if (status == FriendshipStatus.PENDING) {
                return; // request already pending
            }
        }

        // Create a new pending request
        FriendshipEntity entity = new FriendshipEntity();
        entity.setUser(refUser(currentUserId));
        entity.setFriend(refUser(targetUserId));
        entity.setStatus(FriendshipStatus.PENDING);
        entity.setCreatedAt(Instant.now());

        friendshipDao.save(entity);
    }

    @Override
    @Transactional
    public void acceptFriendRequest(UUID currentUserId, UUID fromUserId) {
        // Look for a pending request from fromUserId -> currentUserId
        var pendingOpt = friendshipDao.findPendingRequest(fromUserId, currentUserId);
        FriendshipEntity pending = pendingOpt
                .orElseThrow(() -> new IllegalArgumentException("No pending request from this user."));

        // Mark this row as accepted
        pending.setStatus(FriendshipStatus.ACCEPTED);
        friendshipDao.save(pending);

        // Also create the reverse row (currentUser -> fromUser) as ACCEPTED
        var reverseOpt = friendshipDao.findByUsers(currentUserId, fromUserId);
        if (reverseOpt.isEmpty()) {
            FriendshipEntity reverse = new FriendshipEntity();
            reverse.setUser(refUser(currentUserId));
            reverse.setFriend(refUser(fromUserId));
            reverse.setStatus(FriendshipStatus.ACCEPTED);
            reverse.setCreatedAt(Instant.now());
            friendshipDao.save(reverse);
        } else {
            FriendshipEntity reverse = reverseOpt.get();
            reverse.setStatus(FriendshipStatus.ACCEPTED);
            friendshipDao.save(reverse);
        }
    }

    @Override
    @Transactional
    public void removeFriend(UUID currentUserId, UUID targetUserId) {
        friendshipDao.deleteBetween(currentUserId, targetUserId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FriendActivityDTO> recentActivity(UUID currentUserId) {
        // Placeholder for now; we will implement this using workout data later.
        // Returning an empty list keeps the API stable while you wire the UI.
        return List.of();
    }

    /**
     * Returns a lightweight reference proxy for a UserEntity with the given id,
     * without hitting the database unless necessary.
     */
    private UserEntity refUser(UUID id) {
        UserEntity ref = new UserEntity();
        ref.setId(id);
        return ref;
    }
}
