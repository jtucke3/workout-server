package com.jtucke3.workoutapi.converter.user;

import org.springframework.stereotype.Component;
import com.jtucke3.workoutapi.domain.entity.UserEntity;
import com.jtucke3.workoutapi.dto.user.UserDTO;

@Component
public class UserConv {
    public UserDTO toDto(UserEntity e) {
        return new UserDTO(e.getId(), e.getEmail(), e.getDisplayName());
    }
}
