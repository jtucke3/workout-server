package com.jtucke3.workoutapi.dto.user;

import java.util.UUID;

public record UserDTO(UUID id, String email, String username, String displayName, boolean profilePrivate) {}
