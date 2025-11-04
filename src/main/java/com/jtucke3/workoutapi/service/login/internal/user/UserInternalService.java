package com.jtucke3.workoutapi.service.internal.user;

import com.jtucke3.workoutapi.config.SessionContext;
import com.jtucke3.workoutapi.dao.login.IUserDao;
import com.jtucke3.workoutapi.dto.user.ChangePasswordDTO;
import com.jtucke3.workoutapi.domain.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserInternalService implements IUserInternalService {

    private final IUserDao userDao;
    private final PasswordEncoder encoder;
    private final SessionContext sessionContext;

    @Transactional
    @Override
    public void changePassword(ChangePasswordDTO dto) {
		
        String email = sessionContext.getCurrentUserEmail();

        String currentHash = userDao.findPasswordHashByEmail(email)
            .orElseThrow(() -> new IllegalStateException("User not found"));

        if (!encoder.matches(dto.getCurrentPassword(), currentHash)) {
            throw new IllegalArgumentException("Invalid current password");
        }

        String newHash = encoder.encode(dto.getNewPassword());

        userDao.updatePasswordHashByEmail(email, newHash);
    }
}
