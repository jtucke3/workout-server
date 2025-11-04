package com.jtucke3.workoutapi.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity @Table(name = "workouts")
@Getter @Setter @NoArgsConstructor
public class WorkoutEntity {

    @Id
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.CHAR)              // <-- store UUID as 36-char string
    @Column(columnDefinition = "char(36)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "text")
    private String notes;

    @Column(nullable = false)
    private LocalDateTime workoutAt;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();
}
