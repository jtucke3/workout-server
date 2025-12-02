package com.jtucke3.workoutapi.service.workout.workout.external;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jtucke3.workoutapi.dto.workout.workout.AddExerciseRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.CreateWorkoutRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.GetWorkoutRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.GetWorkoutsRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.RemoveExerciseRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.UpdateWorkoutRequestDTO;
import com.jtucke3.workoutapi.dto.workout.workout.WorkoutResponseDTO;
import com.jtucke3.workoutapi.service.workout.workout.internal.IWorkoutInternalService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkoutExternalService implements IWorkoutExternalService {
    private final IWorkoutInternalService internal;

    @Override
    public WorkoutResponseDTO create(CreateWorkoutRequestDTO req) {
        return internal.create(req);
    }

    @Override
    public WorkoutResponseDTO addExercise(AddExerciseRequestDTO req) {
        return internal.addExercise(req);
    }

    @Override
    public WorkoutResponseDTO removeExercise(RemoveExerciseRequestDTO req) {
        return internal.removeExercise(req);
    }

    @Override
    public WorkoutResponseDTO updateWorkout(UpdateWorkoutRequestDTO req) {
        return internal.updateWorkout(req);
    }

    @Override
    public void removeWorkout(UUID workoutId) {
        internal.removeWorkout(workoutId);
    }

    @Override
    public List<WorkoutResponseDTO> getWorkouts(GetWorkoutsRequestDTO req) {
        return internal.findByUserId(req.getUserId());
    }

    @Override
    public WorkoutResponseDTO getWorkout(GetWorkoutRequestDTO req) {
        return internal.findById(req.getWorkoutId());
    }

    @Override
    public WorkoutResponseDTO getWorkoutById(UUID workoutId) {
        return internal.findById(workoutId);
    }
}
