package com.jtucke3.workoutapi.service.workout.workout.internal;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jtucke3.workoutapi.dao.workout.IWorkoutDao;
import com.jtucke3.workoutapi.domain.entity.WorkoutEntity;
import com.jtucke3.workoutapi.domain.entity.WorkoutExerciseEntity;
import com.jtucke3.workoutapi.dto.workout.workout.AddExerciseRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.CreateWorkoutRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.RemoveExerciseRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.UpdateWorkoutRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.WorkoutResponseDTO;
import com.jtucke3.workoutapi.mappers.workout.workout.WorkoutMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkoutInternalService implements IWorkoutInternalService {

    private final IWorkoutDao workoutDao;

    @Transactional
    @Override
    public WorkoutResponseDTO create(CreateWorkoutRequestDTO req) {
        var workout = workoutDao.createWorkout(req.userId(), req.title(), req.workoutAt(), req.notes());
        return WorkoutMapper.toDTO(workout);
    }

    @Transactional
    @Override
    public WorkoutResponseDTO addExercise(AddExerciseRequestDTO req) {
        var workout = workoutDao.findWorkoutById(req.workoutId())
                .orElseThrow(() -> new IllegalArgumentException("Workout not found"));

        if (workout.getStatus() != WorkoutEntity.Status.IN_PROGRESS) {
            throw new IllegalStateException("Cannot modify a non in-progress workout");
        }

        WorkoutExerciseEntity e = workoutDao.addExercise(workout, req.name(), req.notes(), req.bodyPart(), req.equipment());
        return WorkoutMapper.toDTO(e.getWorkout());
    }

    @Transactional
    @Override
    public WorkoutResponseDTO removeExercise(RemoveExerciseRequestDTO req) {
        var workout = workoutDao.findWorkoutById(req.workoutId())
                .orElseThrow(() -> new IllegalArgumentException("Workout not found"));

        if (workout.getStatus() != WorkoutEntity.Status.IN_PROGRESS) {
            throw new IllegalStateException("Cannot modify a non in-progress workout");
        }

        workoutDao.removeExercise(req.exerciseId());
        return WorkoutMapper.toDTO(workout);
    }

    @Transactional
    @Override
    public WorkoutResponseDTO updateWorkout(UpdateWorkoutRequestDTO req) {
        var workout = workoutDao.findWorkoutById(req.getWorkoutId())
                .orElseThrow(() -> new IllegalArgumentException("Workout not found"));

        if (workout.getStatus() != WorkoutEntity.Status.IN_PROGRESS) {
            throw new IllegalStateException("Cannot update a non in-progress workout");
        }

        var updated = workoutDao.updateWorkout(req.getWorkoutId(), req.getTitle(), req.getWorkoutAt(), req.getNotes());
        return WorkoutMapper.toDTO(updated);
    }

    // --- New method: remove a workout ---
    @Transactional
    @Override
    public void removeWorkout(UUID workoutId) {
        var workout = workoutDao.findWorkoutById(workoutId)
                .orElseThrow(() -> new IllegalArgumentException("Workout not found"));

        if (workout.getStatus() != WorkoutEntity.Status.IN_PROGRESS) {
            throw new IllegalStateException("Cannot remove a non in-progress workout");
        }

        workoutDao.removeWorkout(workoutId);
    }

    // --- Retrieval methods ---
    @Transactional(readOnly = true)
    @Override
    public List<WorkoutResponseDTO> findByUserId(UUID userId) {
        var workouts = workoutDao.findWorkoutsByUserId(userId);
        return workouts.stream()
                       .map(WorkoutMapper::toDTO)
                       .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public WorkoutResponseDTO findById(UUID workoutId) {
        var workout = workoutDao.findWorkoutById(workoutId)
                .orElseThrow(() -> new IllegalArgumentException("Workout not found"));
        return WorkoutMapper.toDTO(workout);
    }
}
