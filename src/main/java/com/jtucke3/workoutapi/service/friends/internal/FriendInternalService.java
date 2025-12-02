package com.jtucke3.workoutapi.service.friends.internal;

import com.jtucke3.workoutapi.dao.friends.IFriendshipDao;
import com.jtucke3.workoutapi.dao.user.IUserAccountDao;
import com.jtucke3.workoutapi.domain.entity.FriendshipEntity;
import com.jtucke3.workoutapi.domain.entity.UserEntity;
import com.jtucke3.workoutapi.domain.enums.FriendshipStatus;
import com.jtucke3.workoutapi.dto.friends.FriendActivityDTO;
import com.jtucke3.workoutapi.dto.friends.FriendPreviewDTO;
import com.jtucke3.workoutapi.dto.friends.FriendProfileDTO;
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
            var friendshipOpt = friendshipDao.findByUsers(currentUserId, u.getId());
            boolean isFriend = friendshipOpt
                    .map(f -> f.getStatus() == FriendshipStatus.ACCEPTED)
                    .orElse(false);
            boolean isPending = friendshipOpt
                    .map(f -> f.getStatus() == FriendshipStatus.PENDING)
                    .orElse(false);

            // In the UI:
            //  - isFriend => show "Friends"
            //  - isPending && !isFriend => show "Pending" (request that I sent)
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
    @Transactional(readOnly = true)
    public List<FriendPreviewDTO> listIncomingRequests(UUID currentUserId) {
        // Users who sent requests TO currentUser (pending)
        List<UserEntity> requesters = friendshipDao.findIncomingRequests(currentUserId);
        List<FriendPreviewDTO> results = new ArrayList<>(requesters.size());

        for (UserEntity u : requesters) {
            results.add(new FriendPreviewDTO(
                    u.getId(),
                    u.getDisplayName(),
                    false,
                    true   // pending from their side
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

        UserEntity target = userDao.findById(targetUserId)
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

        boolean autoAccept = !target.isProfilePrivate();

        // Create a new friendship row currentUser -> target
        FriendshipEntity request = new FriendshipEntity();
        request.setUser(refUser(currentUserId));
        request.setFriend(refUser(targetUserId));
        request.setCreatedAt(Instant.now());
        request.setStatus(autoAccept ? FriendshipStatus.ACCEPTED : FriendshipStatus.PENDING);
        friendshipDao.save(request);

        if (autoAccept) {
            // For public accounts, also ensure the reverse row is ACCEPTED
            var reverseOpt = friendshipDao.findByUsers(targetUserId, currentUserId);
            if (reverseOpt.isEmpty()) {
                FriendshipEntity reverse = new FriendshipEntity();
                reverse.setUser(refUser(targetUserId));
                reverse.setFriend(refUser(currentUserId));
                reverse.setStatus(FriendshipStatus.ACCEPTED);
                reverse.setCreatedAt(Instant.now());
                friendshipDao.save(reverse);
            } else {
                FriendshipEntity reverse = reverseOpt.get();
                reverse.setStatus(FriendshipStatus.ACCEPTED);
                friendshipDao.save(reverse);
            }
        }
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

        // Also create or update the reverse row (currentUser -> fromUser) as ACCEPTED
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
        // Placeholder for now; implement with workout data later.
        return List.of();
    }

    @Override
    @Transactional(readOnly = true)
    public FriendProfileDTO getFriendProfile(UUID currentUserId, UUID friendId) {
        UserEntity friend = userDao.findById(friendId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        boolean isFriend = friendshipDao.findByUsers(currentUserId, friendId)
                .map(f -> f.getStatus() == FriendshipStatus.ACCEPTED)
                .orElse(false);

        boolean sameUser = currentUserId.equals(friendId);

        boolean canViewDetails;
        if (!friend.isProfilePrivate()) {
            canViewDetails = true;
        } else {
            canViewDetails = sameUser || isFriend;
        }

        // For now, workouts/goals are left empty – you can fill these from
        // your workout/goal services later.
        return new FriendProfileDTO(
                friend.getId(),
                friend.getDisplayName(),
                friend.isProfilePrivate(),
                isFriend,
                canViewDetails
        );
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
