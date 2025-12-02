package com.jtucke3.workoutapi.controller.workout.workout;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jtucke3.workoutapi.converter.workout.workout.WorkoutConv;
import com.jtucke3.workoutapi.dto.workout.workout.AddExerciseRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.CreateWorkoutRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.GetWorkoutRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.GetWorkoutsRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.UpdateWorkoutRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.WorkoutResponseDTO;
import com.jtucke3.workoutapi.service.workout.workout.external.IWorkoutExternalService;
import com.jtucke3.workoutapi.webVo.workout.workout.AddExerciseRequestWebVo;
import com.jtucke3.workoutapi.webVo.workout.workout.CreateWorkoutRequestWebVo;
import com.jtucke3.workoutapi.webVo.workout.workout.UpdateWorkoutRequestWebVo;
import com.jtucke3.workoutapi.webVo.workout.workout.WorkoutResponseWebVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final IWorkoutExternalService service;

    /**
     * Create a new IN_PROGRESS workout. TEMP: userId is provided as a request
     * param until JWT is wired in.
     */
    @PostMapping
    public WorkoutResponseWebVo create(@RequestParam("userId") UUID userId,
            @RequestBody CreateWorkoutRequestWebVo webVo) {
        CreateWorkoutRequestDTO dto = WorkoutConv.toCreateWorkoutDTO(userId, webVo);
        WorkoutResponseDTO responseDto = service.create(dto);
        return WorkoutConv.toResponseWebVo(responseDto);
    }

    /**
     * Add an exercise to a workout.
     */
    @PostMapping("/{workoutId}/exercises")
    public WorkoutResponseWebVo addExercise(@PathVariable("workoutId") UUID workoutId,
            @RequestBody AddExerciseRequestWebVo webVo) {
        AddExerciseRequestDTO dto = WorkoutConv.toAddExerciseDTO(workoutId, webVo);
        WorkoutResponseDTO responseDto = service.addExercise(dto);
        return WorkoutConv.toResponseWebVo(responseDto);
    }

    /**
     * Update an existing workout.
     */
    @PutMapping("/{workoutId}")
    public WorkoutResponseWebVo updateWorkout(@PathVariable("workoutId") UUID workoutId,
            @RequestBody UpdateWorkoutRequestWebVo webVo) {
        // Ensure the path variable is set into the WebVo
        webVo.setWorkoutId(workoutId);
        UpdateWorkoutRequestDTO dto = WorkoutConv.toUpdateWorkoutDTO(webVo);
        WorkoutResponseDTO responseDto = service.updateWorkout(dto);
        return WorkoutConv.toResponseWebVo(responseDto);
    }

    /**
     * Get all workouts for a user. TEMP: userId is provided as a request param
     * until JWT is wired in.
     */
    @GetMapping
    public List<WorkoutResponseWebVo> getWorkouts(@RequestParam("userId") UUID userId) {
        GetWorkoutsRequestDTO dto = new GetWorkoutsRequestDTO(userId);
        List<WorkoutResponseDTO> responseDto = service.getWorkouts(dto);
        return responseDto.stream()
                .map(WorkoutConv::toResponseWebVo)
                .toList();
    }

    /**
     * Get a single workout by its ID.
     */
    @GetMapping("/{workoutId}")
    public WorkoutResponseWebVo getWorkout(@PathVariable("workoutId") UUID workoutId) {
        GetWorkoutRequestDTO dto = new GetWorkoutRequestDTO(workoutId);
        WorkoutResponseDTO responseDto = service.getWorkout(dto);
        return WorkoutConv.toResponseWebVo(responseDto);
    }
}
