package com.jtucke3.workoutapi.controller.login;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class LogoutController {

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // since JWT is stateless, logout tells client to discard the token
        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }
}

