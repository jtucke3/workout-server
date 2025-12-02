-- Break a workout into exercises and sets, support "in progress" tracking
ALTER TABLE workouts
    ADD COLUMN status VARCHAR(20) NOT NULL DEFAULT 'IN_PROGRESS'; -- IN_PROGRESS|COMPLETED|CANCELLED

CREATE TABLE workout_exercises (
    id           CHAR(36) NOT NULL PRIMARY KEY,
    workout_id   CHAR(36) NOT NULL,
    name         VARCHAR(120) NOT NULL,
    notes        TEXT,
    equipment    VARCHAR(120) NOT NULL,
    body_part    VARCHAR(120) NOT NULL,
    created_at   TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    CONSTRAINT fk_exercises_workout FOREIGN KEY (workout_id) REFERENCES workouts(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Removed "position" since entity no longer has it
-- Index can be on workout_id + created_at if ordering is needed
CREATE INDEX idx_exercises_workout_created ON workout_exercises (workout_id, created_at);

CREATE TABLE workout_sets (
    id              CHAR(36) NOT NULL PRIMARY KEY,
    exercise_id     CHAR(36) NOT NULL,
    weight          DOUBLE NOT NULL,
    reps            INT NOT NULL,
    created_at      TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    CONSTRAINT fk_sets_exercise FOREIGN KEY (exercise_id) REFERENCES workout_exercises(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx_sets_exercise ON workout_sets (exercise_id, created_at);
