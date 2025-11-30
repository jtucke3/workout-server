package com.jtucke3.workoutapi.service.workout.set.internal;

import org.springframework.stereotype.Service;

import com.jtucke3.workoutapi.dao.workout.IWorkoutSetDao;
import com.jtucke3.workoutapi.domain.entity.WorkoutSetEntity;
import com.jtucke3.workoutapi.dto.workout.set.SetResponseDTO;
import com.jtucke3.workoutapi.dto.workout.set.UpdateSetRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkoutSetInternalService implements IWorkoutSetInternalService {

    private final IWorkoutSetDao dao;

    @Override
    public SetResponseDTO updateSet(UpdateSetRequestDTO req) {
        WorkoutSetEntity set = dao.findById(req.setId())
            .orElseThrow(() -> new IllegalArgumentException("Set not found: " + req.setId()));

        // Apply updates if provided
        if (req.reps() != null) {
            set.setReps(req.reps());
        }
        if (req.weight() != null) {
            set.setWeight(req.weight());
        }

        // Persist changes
        dao.save(set);

        // Return response DTO
        return new SetResponseDTO(
            set.getId(),
            set.getWeight(),
            set.getReps(),
            set.getCreatedAt()
        );
    }
}
