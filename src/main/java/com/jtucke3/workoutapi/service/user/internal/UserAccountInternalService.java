package com.jtucke3.workoutapi.service.user.internal;

import com.jtucke3.workoutapi.converter.user.UserConv;
import com.jtucke3.workoutapi.dao.user.IUserAccountDao;
import com.jtucke3.workoutapi.domain.entity.UserEntity;
import com.jtucke3.workoutapi.dto.user.ChangePasswordRequestDTO;
import com.jtucke3.workoutapi.dto.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountInternalService implements IUserAccountInternalService {

    private final IUserAccountDao dao;
    private final PasswordEncoder passwordEncoder;
    private final UserConv conv;

    @Override
    public UserDTO changePassword(ChangePasswordRequestDTO request) {
        UserEntity user = dao.findById(request.userId())
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!passwordEncoder.matches(request.currentPassword(), user.getPasswordHash())) {
            throw new IllegalArgumentException("Current password is incorrect");
        }

        String newHash = passwordEncoder.encode(request.newPassword());
        user.setPasswordHash(newHash);
        dao.save(user);

        return conv.toDto(user);
    }
}
