package com.jtucke3.workoutapi.dto.friends;

import java.util.UUID;

/**
 * Minimal friend/user preview used for search results and "my friends" list.
 */
public record FriendPreviewDTO(
        UUID id,
        String displayName,
        boolean isFriend,
        boolean isPending
) {}
