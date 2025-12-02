package com.jtucke3.workoutapi.dto.friends;

import java.util.UUID;

/**
 * Minimal profile info for viewing another user's profile
 * with privacy flags. Workouts/goals can be added later as needed.
 */
public record FriendProfileDTO(
        UUID id,
        String displayName,
        boolean profilePrivate,
        boolean isFriend,
        boolean canViewDetails
) {}
