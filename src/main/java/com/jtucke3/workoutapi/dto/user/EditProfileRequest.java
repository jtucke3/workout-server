package com.jtucke3.workoutapi.dto.user;

import java.util.UUID;

public class EditProfileRequest {
    private UUID userId;
    private String displayName;
    private String email;

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}