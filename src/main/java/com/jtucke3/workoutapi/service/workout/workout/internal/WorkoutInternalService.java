package com.jtucke3.workoutapi.service.workout.workout.internal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jtucke3.workoutapi.dao.workout.IWorkoutDao;
import com.jtucke3.workoutapi.domain.entity.WorkoutEntity;
import com.jtucke3.workoutapi.dto.workout.exercise.AddExerciseRequestDTO;
import com.jtucke3.workoutapi.dto.workout.exercise.AddExerciseResponseDTO;
import com.jtucke3.workoutapi.dto.workout.workout.CreateWorkoutRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.CreateWorkoutResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkoutInternalService implements IWorkoutInternalService {

    private final IWorkoutDao workoutDao;

    @Transactional
    @Override
    public CreateWorkoutResponseDTO create(CreateWorkoutRequestDTO req) {
        var w = workoutDao.createWorkout(req.userId(), req.title(), req.workoutAt(), req.notes());
        return new CreateWorkoutResponseDTO(w.getId());
    }

    @Transactional
    @Override
    public AddExerciseResponseDTO addExercise(AddExerciseRequestDTO req) {
        var workout = workoutDao.findWorkoutById(req.workoutId())
                .orElseThrow(() -> new IllegalArgumentException("Workout not found"));

        if (workout.getStatus() != WorkoutEntity.Status.IN_PROGRESS)
            throw new IllegalStateException("Cannot modify a non in-progress workout");

        var ex = workoutDao.addExercise(workout, req.name(), req.notes(), req.position(), req.catalogId());
        return new AddExerciseResponseDTO(ex.getId());
    }
}
