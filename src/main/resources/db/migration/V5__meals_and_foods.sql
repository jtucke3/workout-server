-- Foods library (per user, so people can have personal items)
CREATE TABLE foods (
    id            CHAR(36) NOT NULL PRIMARY KEY,
    user_id       CHAR(36) NOT NULL,
    name          VARCHAR(150) NOT NULL,
    calories      INT NOT NULL,         -- per serving
    protein_g     DECIMAL(6,2) NULL,    -- per serving
    carbs_g       DECIMAL(6,2) NULL,
    fat_g         DECIMAL(6,2) NULL,
    serving_desc  VARCHAR(120) NULL,    -- e.g., "1 scoop (30g)"
    created_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_foods_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx_foods_user ON foods(user_id);
CREATE INDEX idx_foods_name ON foods(name);

-- Meals (aligns with MealEntity)
CREATE TABLE meals (
    id            CHAR(36) NOT NULL PRIMARY KEY,
    user_id       CHAR(36) NOT NULL,
    name          VARCHAR(200) NOT NULL,
    calories      INT NOT NULL,
    meal_at_utc   TIMESTAMP NOT NULL,
    notes         VARCHAR(2000) NULL,
    created_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_meals_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx_meals_user ON meals(user_id);
CREATE INDEX idx_meals_name ON meals(name);
