package com.jtucke3.workoutapi.service.friends.external;

import com.jtucke3.workoutapi.dto.friends.FriendActivityDTO;
import com.jtucke3.workoutapi.dto.friends.FriendPreviewDTO;
import com.jtucke3.workoutapi.service.friends.internal.IFriendInternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FriendExternalService implements IFriendExternalService {

    private final IFriendInternalService internal;

    @Override
    public List<FriendPreviewDTO> searchUsers(UUID currentUserId, String query) {
        return internal.searchUsers(query, currentUserId);
    }

    @Override
    public List<FriendPreviewDTO> listFriends(UUID currentUserId) {
        return internal.listFriends(currentUserId);
    }

    @Override
    public void sendFriendRequest(UUID currentUserId, UUID targetUserId) {
        internal.sendFriendRequest(currentUserId, targetUserId);
    }

    @Override
    public void acceptFriendRequest(UUID currentUserId, UUID fromUserId) {
        internal.acceptFriendRequest(currentUserId, fromUserId);
    }

    @Override
    public void removeFriend(UUID currentUserId, UUID targetUserId) {
        internal.removeFriend(currentUserId, targetUserId);
    }

    @Override
    public List<FriendActivityDTO> recentActivity(UUID currentUserId) {
        return internal.recentActivity(currentUserId);
    }
}
