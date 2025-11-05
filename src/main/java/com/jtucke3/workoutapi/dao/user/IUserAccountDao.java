package com.jtucke3.workoutapi.dao.user;

import com.jtucke3.workoutapi.domain.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface IUserAccountDao {
    Optional<UserEntity> findById(UUID userId);
    void save(UserEntity user);
}
