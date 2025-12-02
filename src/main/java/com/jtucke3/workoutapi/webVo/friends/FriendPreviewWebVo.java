package com.jtucke3.workoutapi.webVo.friends;

import java.util.UUID;

/**
 * Web-layer representation of a friend/user preview.
 */
public class FriendPreviewWebVo {
    private UUID id;
    private String displayName;
    private boolean friend;
    private boolean pending;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isFriend() {
        return friend;
    }

    public void setFriend(boolean friend) {
        this.friend = friend;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }
}
