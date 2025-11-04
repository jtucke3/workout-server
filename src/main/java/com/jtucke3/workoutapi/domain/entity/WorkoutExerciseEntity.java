package com.jtucke3.workoutapi.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.UUID;

@Entity @Table(name = "workout_exercises")
@Getter @Setter @NoArgsConstructor
public class WorkoutExerciseEntity {
    @Id
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(columnDefinition = "char(36)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workout_id", nullable = false)
    private WorkoutEntity workout;

    // Optional link to preset (if chosen from catalog)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalog_id")
    private ExerciseCatalogEntity catalog;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "text")
    private String notes;

    @Column(nullable = false)
    private Integer position;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();
}
