package com.jtucke3.workoutapi.service.friends.internal;

import com.jtucke3.workoutapi.dto.friends.FriendActivityDTO;
import com.jtucke3.workoutapi.dto.friends.FriendPreviewDTO;
import com.jtucke3.workoutapi.dto.friends.FriendProfileDTO;

import java.util.List;
import java.util.UUID;

public interface IFriendInternalService {

    List<FriendPreviewDTO> searchUsers(String query, UUID currentUserId);

    List<FriendPreviewDTO> listFriends(UUID currentUserId);

    List<FriendPreviewDTO> listIncomingRequests(UUID currentUserId);

    void sendFriendRequest(UUID currentUserId, UUID targetUserId);

    void acceptFriendRequest(UUID currentUserId, UUID fromUserId);

    void removeFriend(UUID currentUserId, UUID targetUserId);

    // Future use – for dashboard activity
    List<FriendActivityDTO> recentActivity(UUID currentUserId);

    FriendProfileDTO getFriendProfile(UUID currentUserId, UUID friendId);
}
