package com.jtucke3.workoutapi.dto.friends;

import java.time.Instant;
import java.util.UUID;

/**
 * Placeholder for future dashboard "friends activity".
 * For now we define it so the converter/controller can exist,
 * even if you don't hook it up immediately.
 */
public record FriendActivityDTO(
        UUID friendId,
        String friendName,
        String workoutName,
        Instant occurredAt
) {}
