package com.jtucke3.workoutapi.domain.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "workout_exercises")
@Getter
@Setter
@NoArgsConstructor
public class WorkoutExerciseEntity {

    @Id
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(columnDefinition = "char(36)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workout_id", nullable = false)
    private WorkoutEntity workout;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "text")
    private String notes;

    @Column(nullable = false)
    private String equipment;

    @Column(nullable = false)
    private String bodyPart;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    // ✅ New relationship: Exercise → Sets
    @OneToMany(mappedBy = "exercise",
               cascade = CascadeType.ALL,
               orphanRemoval = true,
               fetch = FetchType.LAZY)
    private List<WorkoutSetEntity> sets = new ArrayList<>();
}
