-- Create users table
CREATE TABLE users (
                       id            CHAR(36)      NOT NULL PRIMARY KEY,
                       email         VARCHAR(255)  NOT NULL UNIQUE,
                       password_hash VARCHAR(255)  NOT NULL,
                       display_name  VARCHAR(100),
                       created_at    TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create workouts table
CREATE TABLE workouts (
                          id          CHAR(36)     NOT NULL PRIMARY KEY,
                          user_id     CHAR(36)     NOT NULL,
                          title       VARCHAR(100) NOT NULL,
                          notes       TEXT,
                          workout_at  DATETIME     NOT NULL,
                          created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          CONSTRAINT fk_workouts_user
                              FOREIGN KEY (user_id) REFERENCES users(id)
                                  ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Optional index for fast queries
CREATE INDEX idx_workouts_user_at ON workouts (user_id, workout_at DESC);
