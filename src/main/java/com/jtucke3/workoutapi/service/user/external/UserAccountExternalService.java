package com.jtucke3.workoutapi.service.user.external;

import com.jtucke3.workoutapi.dto.user.ChangePasswordRequestDTO;
import com.jtucke3.workoutapi.dto.user.UserDTO;
import com.jtucke3.workoutapi.service.user.internal.IUserAccountInternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountExternalService implements IUserAccountExternalService {

    private final IUserAccountInternalService internal;

    @Override
    public UserDTO changePassword(ChangePasswordRequestDTO request) {
        return internal.changePassword(request);
    }
}
