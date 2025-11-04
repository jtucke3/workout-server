package com.jtucke3.workoutapi.controller;

import com.jtucke3.workoutapi.dto.user.UserDTO;
import com.jtucke3.workoutapi.dto.user.UserProfileUpdateDTO;
import com.jtucke3.workoutapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService svc;

    public UserController(UserService svc) { this.svc = svc; }

    @PutMapping("/{id}/profile")
    public ResponseEntity<UserDTO> updateProfile(
            @PathVariable UUID id,
            @RequestBody UserProfileUpdateDTO dto
    ) {
        UserDTO updated = svc.updateProfile(id, dto);
        return ResponseEntity.ok(updated);
    }
}
