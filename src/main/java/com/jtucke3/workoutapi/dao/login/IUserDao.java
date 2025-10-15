package com.jtucke3.workoutapi.dao.login;

import com.jtucke3.workoutapi.dto.user.UserDTO;
import java.util.Optional;

public interface IUserDao {
    Optional<UserDTO> findPublicByEmail(String email);
    Optional<String> findPasswordHashByEmail(String email);
}
