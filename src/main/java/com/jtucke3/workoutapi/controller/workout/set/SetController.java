package com.jtucke3.workoutapi.controller.workout.set;

import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jtucke3.workoutapi.converter.workout.set.SetConv;
import com.jtucke3.workoutapi.dto.workout.set.SetRepsRequestDTO;
import com.jtucke3.workoutapi.dto.workout.set.SetRepsResponseDTO;
import com.jtucke3.workoutapi.dto.workout.set.SetWeightRequestDTO;
import com.jtucke3.workoutapi.dto.workout.set.SetWeightResponseDTO;
import com.jtucke3.workoutapi.service.workout.set.external.IWorkoutSetExternalService;
import com.jtucke3.workoutapi.webVo.workout.set.SetRepsWebVo;
import com.jtucke3.workoutapi.webVo.workout.set.SetWeightWebVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/workouts/{workoutId}/exercises/{exerciseId}/sets")
@RequiredArgsConstructor
public class SetController {

    private final IWorkoutSetExternalService service;

    /**
     * Update the reps for a set.
     */
    @PutMapping("/{setId}/reps")
    public SetRepsResponseDTO setReps(@PathVariable("setId") UUID setId,
                                         @RequestBody SetRepsWebVo webVo) {
        SetRepsRequestDTO dto = SetConv.toSetRepsDTO(setId, webVo);
        return service.setReps(dto);
    }

    /**
     * Update the weight for a set.
     */
    @PutMapping("/{setId}/weight")
    public SetWeightResponseDTO setWeight(@PathVariable("setId") UUID setId,
                                             @RequestBody SetWeightWebVo webVo) {
        SetWeightRequestDTO dto = SetConv.toSetWeightDTO(setId, webVo);
        return service.setWeight(dto);
    }
}
