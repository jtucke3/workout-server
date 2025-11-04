-- Break a workout into exercises and sets, support "in progress" tracking
ALTER TABLE workouts
    ADD COLUMN status VARCHAR(20) NOT NULL DEFAULT 'IN_PROGRESS'; -- IN_PROGRESS|COMPLETED|CANCELLED

CREATE TABLE workout_exercises (
                                   id           CHAR(36) NOT NULL PRIMARY KEY,
                                   workout_id   CHAR(36) NOT NULL,
                                   name         VARCHAR(120) NOT NULL,
                                   notes        TEXT,
                                   position     INT NOT NULL, -- order in the workout
                                   created_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   CONSTRAINT fk_exercises_workout FOREIGN KEY (workout_id) REFERENCES workouts(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx_exercises_workout_pos ON workout_exercises (workout_id, position);

CREATE TABLE workout_sets (
                              id              CHAR(36) NOT NULL PRIMARY KEY,
                              exercise_id     CHAR(36) NOT NULL,
                              set_number      INT NOT NULL,
                              reps            INT NULL,
                              weight_kg       DECIMAL(6,2) NULL,
                              rpe             DECIMAL(3,1) NULL,
                              duration_sec    INT NULL,     -- for timed sets
                              distance_m      INT NULL,     -- for runs/rows
                              created_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              CONSTRAINT fk_sets_exercise FOREIGN KEY (exercise_id) REFERENCES workout_exercises(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx_sets_ex ON workout_sets (exercise_id, set_number);
