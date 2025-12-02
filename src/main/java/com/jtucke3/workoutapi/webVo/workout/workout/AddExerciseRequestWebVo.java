package com.jtucke3.workoutapi.webVo.workout.workout;

import java.util.UUID;
/**
 * Web VO for adding a new exercise to a workout.
 * Can be converted into AddExerciseRequestDTO for service layer use.
 */
public class AddExerciseRequestWebVo {
    private UUID workoutId;
    private String name;
    private String notes;
    private String equipment;
    private String bodyPart;

    public UUID getWorkoutId() { return workoutId; }
    public void setWorkoutId(UUID workoutId) { this.workoutId = workoutId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getEquipment() { return equipment; }
    public void setEquipment(String equipment) { this.equipment = equipment; }

    public String getBodyPart() { return bodyPart; }
    public void setBodyPart(String bodyPart) { this.bodyPart = bodyPart; }
}
