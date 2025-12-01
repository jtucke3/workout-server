package com.jtucke3.workoutapi.converter.mealTracking;

import com.jtucke3.workoutapi.webVo.mealTracking.CreateMealRequestWebVo;
import com.jtucke3.workoutapi.webVo.mealTracking.UpdateMealRequestWebVo;
import com.jtucke3.workoutapi.webVo.mealTracking.MealResponseWebVo;
import com.jtucke3.workoutapi.dto.mealTracking.CreateMealRequestDTO;
import com.jtucke3.workoutapi.dto.mealTracking.UpdateMealRequestDTO;
import com.jtucke3.workoutapi.dto.mealTracking.MealResponseDTO;

/**
 * Converter between WebVOs (API layer) and DTOs (service layer).
 */
public class MealConv {

    // --- WebVO → DTO ---
    public static CreateMealRequestDTO toDto(CreateMealRequestWebVo vo) {
        return new CreateMealRequestDTO(
                vo.userId(),
                vo.name(),
                vo.calories(),
                vo.mealAtUtc(),
                vo.notes()
        );
    }

    public static UpdateMealRequestDTO toDto(UpdateMealRequestWebVo vo) {
        return new UpdateMealRequestDTO(
                vo.mealId(),
                vo.name(),
                vo.calories(),
                vo.mealAtUtc(),
                vo.notes()
        );
    }

    // --- DTO → WebVO ---
    public static MealResponseWebVo toWebVo(MealResponseDTO dto) {
        return new MealResponseWebVo(
                dto.id(),
                dto.userId(),
                dto.name(),
                dto.calories(),
                dto.mealAtUtc(),
                dto.notes(),
                dto.createdAt()
        );
    }
}
