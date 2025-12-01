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
import com.jtucke3.workoutapi.dto.workout.exercise.ExerciseResponseDTO;
import com.jtucke3.workoutapi.dto.workout.exercise.RemoveSetRequestDTO;
import com.jtucke3.workoutapi.service.workout.exercise.external.IWorkoutExerciseExternalService;
import com.jtucke3.workoutapi.webVo.workout.exercise.AddSetRequestWebVo;
import com.jtucke3.workoutapi.webVo.workout.exercise.ExerciseResponseWebVo;
import com.jtucke3.workoutapi.webVo.workout.exercise.RemoveSetRequestWebVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/workouts/{workoutId}/exercises/{exerciseId}/sets")
@RequiredArgsConstructor
public class ExerciseController {

    private final IWorkoutExerciseExternalService service;

    /**
     * Add a new set to an exercise.
     * The exerciseId comes from the path; body carries set details.
     */
    @PostMapping
    public ExerciseResponseWebVo addSet(@PathVariable("exerciseId") UUID exerciseId,
                                        @RequestBody AddSetRequestWebVo webVo) {
        AddSetRequestDTO dto = ExerciseConv.toAddSetDTO(exerciseId, webVo);
        ExerciseResponseDTO responseDto = service.addSet(dto);
        return ExerciseConv.toResponseWebVo(responseDto);
    }

    /**
     * Remove a set from an exercise.
     * The setId comes from the WebVo body.
     */
    @DeleteMapping
    public ExerciseResponseWebVo removeSet(@PathVariable("exerciseId") UUID exerciseId,
                                           @RequestBody RemoveSetRequestWebVo webVo) {
        RemoveSetRequestDTO dto = ExerciseConv.toRemoveSetDTO(webVo);
        ExerciseResponseDTO responseDto = service.removeSet(dto);
        return ExerciseConv.toResponseWebVo(responseDto);
    }
}
