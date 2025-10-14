package com.jtucke3.workoutapi;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "*") // Allows frontend calls during development
public class loginController {

    // GET endpoint to verify backend is working
    @GetMapping
    public String loginStatus() {
        return "Backend is running and LoginController is active!";
    }

    // POST endpoint placeholder for future login logic
    @PostMapping
    public String login(@RequestParam String username, @RequestParam String password) {
        // For now, just echo the data back
        return "Received login request for user: " + username;
    }
}
