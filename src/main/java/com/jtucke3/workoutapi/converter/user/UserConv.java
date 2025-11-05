package com.jtucke3.workoutapi.converter.user;

import com.jtucke3.workoutapi.dto.user.ChangePasswordRequestDTO;
import com.jtucke3.workoutapi.dto.user.UserDTO;
import com.jtucke3.workoutapi.domain.entity.UserEntity;
import com.jtucke3.workoutapi.webVo.user.ChangePasswordWebRequestVo;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserConv {

    public UserDTO toDto(UserEntity e) {
        return new UserDTO(e.getId(), e.getEmail(), e.getDisplayName());
    }

    public static ChangePasswordRequestDTO changePasswordReuquestToDTO(UUID userId, ChangePasswordWebRequestVo vo) {
        return new ChangePasswordRequestDTO(userId, vo.getCurrentPassword(), vo.getNewPassword());
    }

    public static UserProfileWVO userProfileWebRequestToWVO(UUID userId, UserProfileWebRequestVo webVo) {
    return new UserProfileWVO(webVo.getUsername(), webVo.getEmail());
}

}
