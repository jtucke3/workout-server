package com.jtucke3.workoutapi.service.user.internal;

import com.jtucke3.workoutapi.dto.user.ChangePasswordRequestDTO;
import com.jtucke3.workoutapi.dto.user.UserDTO;

public interface IUserAccountInternalService {
    UserDTO changePassword(ChangePasswordRequestDTO request);
}
