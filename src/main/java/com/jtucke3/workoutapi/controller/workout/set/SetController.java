package com.jtucke3.workoutapi.controller.workout.set;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jtucke3.workoutapi.converter.workout.set.SetConv;
import com.jtucke3.workoutapi.dto.workout.set.SetResponseDTO;
import com.jtucke3.workoutapi.dto.workout.set.UpdateSetRequestDTO;
import com.jtucke3.workoutapi.service.workout.set.external.IWorkoutSetExternalService;
import com.jtucke3.workoutapi.webVo.workout.set.SetResponseWebVo;
import com.jtucke3.workoutapi.webVo.workout.set.UpdateSetRequestWebVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/workouts/{workoutId}/exercises/{exerciseId}/sets")
@RequiredArgsConstructor
public class SetController {

    private final IWorkoutSetExternalService service;

    /**
     * Update reps and/or weight for a set.
     */
    @PutMapping("/{setId}")
    public ResponseEntity<SetResponseWebVo> updateSet(
            @PathVariable("setId") UUID setId,
            @RequestBody UpdateSetRequestWebVo webVo) {

        // Convert to DTO and call service
        UpdateSetRequestDTO dto = SetConv.toDto(webVo);
        SetResponseDTO responseDto = service.updateSet(dto);

        // Convert back to WebVo for response
        return ResponseEntity.ok(SetConv.toWebVo(responseDto));
    }
}
