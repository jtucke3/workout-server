package com.jtucke3.workoutapi.converter.user;

import com.jtucke3.workoutapi.dto.user.ChangePasswordRequestDTO;
import com.jtucke3.workoutapi.dto.user.UserDTO;
import com.jtucke3.workoutapi.domain.entity.UserEntity;
import com.jtucke3.workoutapi.webVo.user.ChangePasswordWebRequestVo;
import com.jtucke3.workoutapi.webVo.user.UserProfileWVO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserConv {

    public UserDTO toDto(UserEntity e) {
        // (id, email, username, displayName, profilePrivate)
        return new UserDTO(
                e.getId(),
                e.getEmail(),
                e.getUsername(),
                e.getDisplayName(),
                e.isProfilePrivate()
        );
    }

    public static ChangePasswordRequestDTO changePasswordReuquestToDTO(UUID userId, ChangePasswordWebRequestVo vo) {
        return new ChangePasswordRequestDTO(userId, vo.getCurrentPassword(), vo.getNewPassword());
    }

    public static UserProfileWVO userProfileWebRequestToWVO(UUID userId, UserProfileWVO webVo) {
        return new UserProfileWVO(
                webVo.getUsername(),
                webVo.getEmail(),
                webVo.isProfilePrivate()
        );
    }
}
