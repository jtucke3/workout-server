package com.jtucke3.workoutapi.service.internal.user;

import com.jtucke3.workoutapi.dto.user.ChangePasswordDTO;

public interface IUserInternalService {
    void changePassword(ChangePasswordDTO dto);
}

