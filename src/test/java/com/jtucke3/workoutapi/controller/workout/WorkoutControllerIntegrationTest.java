package com.jtucke3.workoutapi.controller.workout;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WorkoutControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateWorkout() throws Exception {
        UUID userId = UUID.randomUUID();
        String requestBody = """
            {
                "title": "Morning Workout",
                "workoutAt": "2025-11-20T08:00:00",
                "notes": "Chest and triceps"
            }
            """;

        mockMvc.perform(post("/api/workouts")
                .param("userId", userId.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());
    }
}
