package com.jtucke3.workoutapi.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.UUID;

@Entity @Table(name = "exercise_catalog")
@Getter @Setter @NoArgsConstructor
public class ExerciseCatalogEntity {
    @Id
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(columnDefinition = "char(36)")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    private String bodyPart;
    private String equipment;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();
}
