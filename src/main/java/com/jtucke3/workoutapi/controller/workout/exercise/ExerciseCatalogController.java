package com.jtucke3.workoutapi.controller.workout.exercise;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jtucke3.workoutapi.dto.workout.exercise.ExerciseCatalogItemDTO;
import com.jtucke3.workoutapi.service.workout.exercise.external.IExerciseCatalogExternalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/exercises")
@RequiredArgsConstructor
public class ExerciseCatalogController {

    private final IExerciseCatalogExternalService service;

    /**
     * Search preset exercises (catalog). If q is empty, returns top-N alphabetically.
     */
    @GetMapping("/catalog")
    public List<ExerciseCatalogItemDTO> search(@RequestParam(value = "q", required = false) String q,
                                               @RequestParam(value = "limit", required = false, defaultValue = "50") int limit) {
        return service.search(q, limit);
    }
}
