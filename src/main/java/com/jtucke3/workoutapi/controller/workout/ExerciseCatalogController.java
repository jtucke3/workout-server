package com.jtucke3.workoutapi.controller.workout;

import com.jtucke3.workoutapi.dto.workout.ExerciseCatalogItemDTO;
import com.jtucke3.workoutapi.service.workout.external.IExerciseCatalogExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
