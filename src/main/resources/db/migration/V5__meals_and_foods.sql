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

-- Meal templates a user can reuse quickly (e.g., "Breakfast: Oats + Eggs")
CREATE TABLE meal_templates (
                                id            CHAR(36) NOT NULL PRIMARY KEY,
                                user_id       CHAR(36) NOT NULL,
                                name          VARCHAR(120) NOT NULL,
                                created_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                CONSTRAINT fk_templates_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE meal_template_items (
                                     id               CHAR(36) NOT NULL PRIMARY KEY,
                                     template_id      CHAR(36) NOT NULL,
                                     food_id          CHAR(36) NOT NULL,
                                     quantity         DECIMAL(8,2) NOT NULL DEFAULT 1.0, -- multiplier of serving
                                     created_at       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                     CONSTRAINT fk_titems_template FOREIGN KEY (template_id) REFERENCES meal_templates(id) ON DELETE CASCADE,
                                     CONSTRAINT fk_titems_food FOREIGN KEY (food_id) REFERENCES foods(id) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx_titems_template ON meal_template_items(template_id);

-- Daily meal logs (by date) and items
CREATE TABLE meal_logs (
                           id            CHAR(36) NOT NULL PRIMARY KEY,
                           user_id       CHAR(36) NOT NULL,
                           log_date      DATE NOT NULL,
                           created_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           CONSTRAINT fk_mlogs_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                           CONSTRAINT uc_mlogs UNIQUE (user_id, log_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE meal_log_items (
                                id            CHAR(36) NOT NULL PRIMARY KEY,
                                meal_log_id   CHAR(36) NOT NULL,
                                food_id       CHAR(36) NOT NULL,
                                quantity      DECIMAL(8,2) NOT NULL DEFAULT 1.0,
                                created_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                CONSTRAINT fk_mitems_log FOREIGN KEY (meal_log_id) REFERENCES meal_logs(id) ON DELETE CASCADE,
                                CONSTRAINT fk_mitems_food FOREIGN KEY (food_id) REFERENCES foods(id) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx_mitems_log ON meal_log_items(meal_log_id);
