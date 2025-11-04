package com.jtucke3.workoutapi.service;

import com.jtucke3.workoutapi.dao.login.IUserDao;
import com.jtucke3.workoutapi.dto.user.UserDTO;
import com.jtucke3.workoutapi.dto.user.UserProfileUpdateDTO;
import com.jtucke3.workoutapi.domain.entity.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@Service
public class UserService {

    private final IUserDao userDao;

    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public UserDTO updateProfile(UUID userId, UserProfileUpdateDTO dto) {
        UserEntity entity = userDao.findEntityById(userId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (dto.email() != null && !dto.email().equalsIgnoreCase(entity.getEmail())) {
            if (userDao.existsByEmail(dto.email())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
            }
            entity.setEmail(dto.email().toLowerCase());
        }

        if (dto.displayName() != null) entity.setDisplayName(dto.displayName());
        if (dto.twoFactorEnabled() != null) entity.setTwoFactorEnabled(dto.twoFactorEnabled());

        UserEntity saved = userDao.saveEntity(entity);
        return new UserDTO(saved.getId(), saved.getEmail(), saved.getDisplayName());
    }
}
