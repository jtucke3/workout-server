package com.jtucke3.workoutapi.controller.workout.workout;

import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jtucke3.workoutapi.converter.workout.workout.WorkoutConv;
import com.jtucke3.workoutapi.dto.workout.workout.AddExerciseRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.AddExerciseResponseDTO;
import com.jtucke3.workoutapi.dto.workout.workout.CreateWorkoutRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.CreateWorkoutResponseDTO;
import com.jtucke3.workoutapi.service.workout.workout.external.IWorkoutExternalService;
import com.jtucke3.workoutapi.webVo.workout.workout.AddExerciseWebVo;
import com.jtucke3.workoutapi.webVo.workout.workout.CreateWorkoutWebVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final IWorkoutExternalService service;

    /**
     * Create a new IN_PROGRESS workout.
     * TEMP: userId is provided as a request param until JWT is wired in.
     * Later, we’ll pull the user from the auth principal instead of the query param.
     */
    @PostMapping
    public CreateWorkoutResponseDTO create(@RequestParam("userId") UUID userId,
                                           @RequestBody CreateWorkoutWebVo webVo) {
        CreateWorkoutRequestDTO dto = WorkoutConv.toCreateWorkoutDTO(userId, webVo);
        return service.create(dto);
    }

    /**
     * Add an exercise to a workout. Supports either a catalogId (preset) or custom name.
     * The workoutId comes from the path; other fields come from the WebVo body.
     */
    @PostMapping("/{workoutId}/exercises")
    public AddExerciseResponseDTO addExercise(@PathVariable("workoutId") UUID workoutId,
                                              @RequestBody AddExerciseWebVo webVo) {
        AddExerciseRequestDTO dto = WorkoutConv.toAddExerciseDTO(workoutId, webVo);
        return service.addExercise(dto);
    }
}
