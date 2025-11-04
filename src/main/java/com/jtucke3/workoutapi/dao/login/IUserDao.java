package com.jtucke3.workoutapi.dao.login;

import com.jtucke3.workoutapi.dto.user.UserDTO;
import java.util.Optional;

public interface IUserDao {
    Optional<UserDTO> findPublicByEmail(String email);
    Optional<String> findPasswordHashByEmail(String email);

    // NEW:
    boolean existsByEmail(String email);
    UserDTO saveNew(String email, String displayName, String passwordHash);
    Optional<String> findTwoFactorSecretByEmail(String email);
    void storeTwoFactorSecret(String email, String secret, boolean enabled);
}
