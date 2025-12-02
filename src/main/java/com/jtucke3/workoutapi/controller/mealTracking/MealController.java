package com.jtucke3.workoutapi.controller.mealTracking;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jtucke3.workoutapi.converter.mealTracking.MealConv;
import com.jtucke3.workoutapi.dto.mealTracking.MealResponseDTO;
import com.jtucke3.workoutapi.service.mealTracking.external.IMealExternalService;
import com.jtucke3.workoutapi.webVo.mealTracking.CreateMealRequestWebVo;
import com.jtucke3.workoutapi.webVo.mealTracking.MealResponseWebVo;
import com.jtucke3.workoutapi.webVo.mealTracking.UpdateMealRequestWebVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/meals/{userId}")
@RequiredArgsConstructor
public class MealController {

    private final IMealExternalService mealService;

    // --- Create ---
    @PostMapping
    public ResponseEntity<MealResponseWebVo> createMeal(
            @PathVariable UUID userId,
            @RequestBody CreateMealRequestWebVo vo) {
        // ensure path userId is used
        CreateMealRequestWebVo fixedVo = new CreateMealRequestWebVo(
                userId,
                vo.name(),
                vo.calories(),
                vo.mealAtUtc(),
                vo.notes()
        );
        MealResponseDTO dto = mealService.createMeal(MealConv.toDto(fixedVo));
        return ResponseEntity.ok(MealConv.toWebVo(dto));
    }

    // --- Update ---
    @PutMapping("/{mealId}")
    public ResponseEntity<MealResponseWebVo> updateMeal(
            @PathVariable UUID userId,
            @PathVariable UUID mealId,
            @RequestBody UpdateMealRequestWebVo vo) {
        // ensure path IDs match body IDs
        UpdateMealRequestWebVo fixedVo = new UpdateMealRequestWebVo(
                mealId,
                userId,
                vo.name(),
                vo.calories(),
                vo.mealAtUtc(),
                vo.notes()
        );
        MealResponseDTO dto = mealService.updateMeal(MealConv.toDto(fixedVo));
        return ResponseEntity.ok(MealConv.toWebVo(dto));
    }

    // --- Get by ID ---
    @GetMapping("/{mealId}")
    public ResponseEntity<MealResponseWebVo> getMealById(
            @PathVariable UUID mealId) {
        MealResponseDTO dto = mealService.getMealById(mealId);
        return ResponseEntity.ok(MealConv.toWebVo(dto));
    }

    // --- Get by User ---
    @GetMapping
    public ResponseEntity<List<MealResponseWebVo>> getMealsByUser(
            @PathVariable UUID userId) {
        List<MealResponseDTO> dtos = mealService.getMealsByUserId(userId);
        return ResponseEntity.ok(
                dtos.stream().map(MealConv::toWebVo).toList()
        );
    }

    // --- Delete ---
    @DeleteMapping("/{mealId}")
    public ResponseEntity<Void> deleteMeal(@PathVariable UUID mealId) {
        mealService.deleteMeal(mealId);
        return ResponseEntity.noContent().build();
    }
}
