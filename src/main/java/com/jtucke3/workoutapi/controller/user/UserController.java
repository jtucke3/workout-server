package com.jtucke3.workoutapi.controller.user;

import com.jtucke3.workoutapi.dto.user.ChangePasswordDTO;
import com.jtucke3.workoutapi.service.user.internal.IUserInternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserInternalService service;

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO dto) {
        service.changePassword(dto);
        return ResponseEntity.ok().build();
    }
}
