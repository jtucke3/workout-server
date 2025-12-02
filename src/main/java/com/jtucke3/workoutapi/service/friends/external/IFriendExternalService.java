package com.jtucke3.workoutapi.service.friends.external;

import com.jtucke3.workoutapi.dto.friends.FriendActivityDTO;
import com.jtucke3.workoutapi.dto.friends.FriendPreviewDTO;

import java.util.List;
import java.util.UUID;

public interface IFriendExternalService {

    List<FriendPreviewDTO> searchUsers(UUID currentUserId, String query);

    List<FriendPreviewDTO> listFriends(UUID currentUserId);

    void sendFriendRequest(UUID currentUserId, UUID targetUserId);

    void acceptFriendRequest(UUID currentUserId, UUID fromUserId);

    void removeFriend(UUID currentUserId, UUID targetUserId);

    List<FriendActivityDTO> recentActivity(UUID currentUserId);
}
