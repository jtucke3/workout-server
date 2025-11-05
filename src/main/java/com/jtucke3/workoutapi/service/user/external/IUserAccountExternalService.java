package com.jtucke3.workoutapi.service.user.external;

import com.jtucke3.workoutapi.dto.user.ChangePasswordRequestDTO;
import com.jtucke3.workoutapi.dto.user.UserDTO;

public interface IUserAccountExternalService {
    UserDTO changePassword(ChangePasswordRequestDTO request);
}
