package com.jtucke3.workoutapi.domain.entity.mealTracking;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import com.jtucke3.workoutapi.domain.entity.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "meals")
@Getter @Setter @NoArgsConstructor
public class MealEntity {

    @Id
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(columnDefinition = "char(36)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private String name;                      // e.g. "Breakfast", "Chicken Salad"

    @Column(nullable = false)
    private int calories;                     // calorie count

    @Column(nullable = false)
    private Instant mealAtUtc;                // when the meal was eaten, stored in UTC

    @Column(length = 2000)
    private String notes;                     // optional notes, e.g. macros, mood, etc.

    @Column(nullable = false)
    private Instant createdAt = Instant.now(); // record creation timestamp
}
