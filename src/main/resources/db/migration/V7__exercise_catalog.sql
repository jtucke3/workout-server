-- Preset exercise catalog
CREATE TABLE exercise_catalog (
                                  id          CHAR(36) NOT NULL PRIMARY KEY,
                                  name        VARCHAR(120) NOT NULL UNIQUE,
                                  body_part   VARCHAR(50) NULL,
                                  equipment   VARCHAR(50) NULL,
                                  created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Link workout exercises -> optional catalog item
ALTER TABLE workout_exercises
    ADD COLUMN catalog_id CHAR(36) NULL,
  ADD CONSTRAINT fk_exercises_catalog
    FOREIGN KEY (catalog_id) REFERENCES exercise_catalog(id) ON DELETE SET NULL;

-- Nice starter presets (optional seed)
INSERT INTO exercise_catalog (id, name, body_part, equipment) VALUES
        (UUID(), 'Barbell Back Squat', 'Legs', 'Barbell'),
        (UUID(), 'Bench Press', 'Chest', 'Barbell'),
        (UUID(), 'Deadlift', 'Back', 'Barbell'),
        (UUID(), 'Overhead Press', 'Shoulders', 'Barbell'),
        (UUID(), 'Pull-Up', 'Back', 'Bodyweight'),
        (UUID(), 'Dumbbell Row', 'Back', 'Dumbbell'),
        (UUID(), 'Lat Pulldown', 'Back', 'Machine'),
        (UUID(), 'Leg Press', 'Legs', 'Machine'),
        (UUID(), 'Incline Dumbbell Press', 'Chest', 'Dumbbell');
