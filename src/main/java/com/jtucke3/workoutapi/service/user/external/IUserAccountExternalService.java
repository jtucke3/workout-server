package com.jtucke3.workoutapi.service.user.external;

import com.jtucke3.workoutapi.dto.user.ChangePasswordRequestDTO;
import com.jtucke3.workoutapi.dto.user.UserDTO;
import com.jtucke3.workoutapi.webVo.user.UserProfileWVO;
import java.util.UUID;

public interface IUserAccountExternalService {
    UserDTO changePassword(ChangePasswordRequestDTO request);
    UserDTO updateProfile(UUID userId, UserProfileWVO wvo);
}
