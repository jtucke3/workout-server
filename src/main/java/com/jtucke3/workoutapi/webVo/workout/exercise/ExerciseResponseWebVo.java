package com.jtucke3.workoutapi.webVo.workout.exercise;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.jtucke3.workoutapi.webVo.workout.set.SetResponseWebVo;

/**
 * Web VO for returning exercise details to the client.
 * Mirrors ExerciseResponseDTO fields and includes sets.
 */
public class ExerciseResponseWebVo {
    private UUID id;
    private String name;
    private String notes;
    private String equipment;
    private String bodyPart;
    private Instant createdAt;
    private List<SetResponseWebVo> sets;   // ✅ include sets

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getEquipment() { return equipment; }
    public void setEquipment(String equipment) { this.equipment = equipment; }

    public String getBodyPart() { return bodyPart; }
    public void setBodyPart(String bodyPart) { this.bodyPart = bodyPart; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public List<SetResponseWebVo> getSets() { return sets; }
    public void setSets(List<SetResponseWebVo> sets) { this.sets = sets; }
}
