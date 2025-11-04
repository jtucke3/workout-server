-- Simple account privacy: PUBLIC or FOLLOWERS_ONLY (block feed/profile if not allowed)
ALTER TABLE users
    ADD COLUMN privacy VARCHAR(20) NOT NULL DEFAULT 'PUBLIC'; -- PUBLIC|FOLLOWERS_ONLY
CREATE INDEX idx_users_privacy ON users(privacy);
