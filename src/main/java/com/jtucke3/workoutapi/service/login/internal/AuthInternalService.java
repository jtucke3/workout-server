package com.jtucke3.workoutapi.service.login.internal;

import com.jtucke3.workoutapi.dao.login.IUserDao;
import com.jtucke3.workoutapi.dto.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthInternalService implements IAuthInternalService {

    private final IUserDao userDao;
    private final PasswordEncoder encoder;

    @Transactional(readOnly = true)
    public UserDTO validateCredentials(String email, String rawPassword) {
        var hash = userDao.findPasswordHashByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
        if (!encoder.matches(rawPassword, hash))
            throw new IllegalArgumentException("Invalid credentials");
        return userDao.findPublicByEmail(email)
                .orElseThrow(() -> new IllegalStateException("User record missing after hash lookup"));
    }

    public String issueToken(UserDTO user) {
        // Placeholder; replace with JWT later.
        return user.id().toString();
    }
}
