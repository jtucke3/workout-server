-- Users follow other users (unidirectional "following")
CREATE TABLE user_follows (
                              id           CHAR(36) NOT NULL PRIMARY KEY,
                              follower_id  CHAR(36) NOT NULL,
                              followee_id  CHAR(36) NOT NULL,
                              created_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              CONSTRAINT fk_follows_follower FOREIGN KEY (follower_id) REFERENCES users(id) ON DELETE CASCADE,
                              CONSTRAINT fk_follows_followee FOREIGN KEY (followee_id) REFERENCES users(id) ON DELETE CASCADE,
                              CONSTRAINT uc_follows UNIQUE (follower_id, followee_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx_follows_followee ON user_follows (followee_id);
CREATE INDEX idx_follows_follower ON user_follows (follower_id);
