package com.jtucke3.workoutapi.controller.workout.exercise;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jtucke3.workoutapi.converter.workout.exercise.ExerciseConv;
import com.jtucke3.workoutapi.dto.workout.exercise.AddSetRequestDTO;
import com.jtucke3.workoutapi.dto.workout.exercise.AddSetResponseDTO;
import com.jtucke3.workoutapi.dto.workout.exercise.RemoveSetRequestDTO;
import com.jtucke3.workoutapi.dto.workout.exercise.RemoveSetResponseDTO;
import com.jtucke3.workoutapi.service.workout.exercise.external.IWorkoutExerciseExternalService;
import com.jtucke3.workoutapi.webVo.workout.exercise.AddSetWebVo;
import com.jtucke3.workoutapi.webVo.workout.exercise.RemoveSetWebVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/workouts/{workoutId}/exercises/{exerciseId}/sets")
@RequiredArgsConstructor
public class ExerciseController {

    private final IWorkoutExerciseExternalService service;

    /**
     * Add a new blank set to an exercise.
     * The exerciseId comes from the path; body is a blank WebVo for consistency.
     */
    @PostMapping
    public AddSetResponseDTO addSet(@PathVariable("exerciseId") UUID exerciseId,
                                    @RequestBody AddSetWebVo webVo) {
        AddSetRequestDTO dto = ExerciseConv.toAddSetDTO(exerciseId, webVo);
        return service.addSet(dto);
    }

    /**
     * Remove a set from an exercise.
     * The setId comes from the WebVo body.
     */
    @DeleteMapping
    public RemoveSetResponseDTO removeSet(@PathVariable("exerciseId") UUID exerciseId,
                                          @RequestBody RemoveSetWebVo webVo) {
        RemoveSetRequestDTO dto = ExerciseConv.toRemoveSetDTO(webVo);
        return service.removeSet(dto);
    }
}
