package com.jtucke3.workoutapi.dto.login;

import com.jtucke3.workoutapi.dto.user.UserDTO;

public record LoginResponseDTO(String token, UserDTO user) {}
