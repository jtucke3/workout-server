package com.jtucke3.workoutapi.controller.workout;

import com.jtucke3.workoutapi.dao.login.IUserDao;
import com.jtucke3.workoutapi.dao.login.UserDao;
import com.jtucke3.workoutapi.domain.entity.UserEntity;
import com.jtucke3.workoutapi.dto.user.UserDTO;
import com.jtucke3.workoutapi.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class WorkoutControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @PersistenceContext private EntityManager em;

    @Test
    void shouldCreateWorkout() throws Exception {
        // Create and save a test user
        UserEntity user = new UserEntity();
        user.setDisplayName("Test User");
        user.setEmail("test1223334444@example.com");
        user.setPasswordHash("password");

        em.persist(user);
        em.flush();

        String userId = user.getId().toString();

        // JSON request body
        String requestBody = """
            {
                "title": "Morning Workout",
                "workoutAt": "2025-11-20T08:00:00",
                "notes": "Chest and triceps"
            }
            """;

        // Perform the POST request
        mockMvc.perform(
                post("/api/workouts")
                        .param("userId", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        )
        .andExpect(status().isOk());
    }
}
