package com.jtucke3.workoutapi.webVo.workout.workout;

import java.util.UUID;

public class AddExerciseWebVo {
    private UUID catalogId;
    private String name;
    private String notes;
    private Integer position;

    public UUID getCatalogId() { return catalogId; }
    public void setCatalogId(UUID catalogId) { this.catalogId = catalogId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public Integer getPosition() { return position; }
    public void setPosition(Integer position) { this.position = position; }
}
