CREATE TABLE friendships (
                             id CHAR(36) NOT NULL,
                             user_id CHAR(36) NOT NULL,
                             friend_id CHAR(36) NOT NULL,
                             status VARCHAR(32) NOT NULL,
                             created_at DATETIME(6) NOT NULL,

                             CONSTRAINT pk_friendships PRIMARY KEY (id),

                             CONSTRAINT uq_friendships_user_friend UNIQUE (user_id, friend_id),

                             CONSTRAINT fk_friendships_user
                                 FOREIGN KEY (user_id) REFERENCES users (id)
                                     ON DELETE CASCADE,

                             CONSTRAINT fk_friendships_friend
                                 FOREIGN KEY (friend_id) REFERENCES users (id)
                                     ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
