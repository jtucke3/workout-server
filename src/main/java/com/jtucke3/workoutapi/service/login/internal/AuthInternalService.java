package com.jtucke3.workoutapi.service.login.internal;

import com.jtucke3.workoutapi.config.JwtUtil;
import com.jtucke3.workoutapi.dao.login.IUserDao;
import com.jtucke3.workoutapi.dto.login.LoginResponseDTO;
import com.jtucke3.workoutapi.dto.login.RegisterRequestDTO;
import com.jtucke3.workoutapi.dto.login.Verify2FARequestDTO;
import com.jtucke3.workoutapi.dto.user.UserDTO;
import com.jtucke3.workoutapi.service.login.internal.challenge.ILoginChallengeInternalService;
import com.jtucke3.workoutapi.service.login.internal.totp.ITotpInternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthInternalService implements IAuthInternalService {

    private final IUserDao userDao;
    private final PasswordEncoder encoder;
    private final JwtUtil jwt;

    private final ITotpInternalService totp;
    private final ILoginChallengeInternalService challenges;

    @Transactional(readOnly = true)
    @Override
    public UserDTO validateCredentials(String email, String rawPassword) {
        var hash = userDao.findPasswordHashByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
        if (!encoder.matches(rawPassword, hash))
            throw new IllegalArgumentException("Invalid credentials");
        return userDao.findPublicByEmail(email)
                .orElseThrow(() -> new IllegalStateException("User record missing after hash lookup"));
    }

    @Override
    public String issueToken(UserDTO user) {
        return jwt.generate(
                user.email(),
                Map.of("uid", user.id(), "email", user.email(), "name", user.displayName())
        );
    }

    @Transactional
    @Override
    public UserDTO register(RegisterRequestDTO req) {
        if (userDao.existsByEmail(req.email())) {
            throw new IllegalArgumentException("Email already in use");
        }
        var hash = encoder.encode(req.password());
        return userDao.saveNew(req.email(), req.displayName(), hash);
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponseDTO startLogin(String email, String password) {
        var user = validateCredentials(email, password);

        boolean twoFactorEnabled = userDao.isTwoFactorEnabled(email);

        if (twoFactorEnabled) {
            var challengeId = challenges.create(email.toLowerCase());
            // 2FA is configured, and we require verification now
            return LoginResponseDTO.needs2FA(challengeId, true);
        } else {
            // No 2FA configured yet, regular token login
            return LoginResponseDTO.token(user, issueToken(user), false);
        }
    }

    // 2) verify2fa – successful verification implies 2FA is configured
    @Override
    @Transactional
    public LoginResponseDTO verify2fa(Verify2FARequestDTO req) {
        var email = challenges.consume(req.challengeId())
                .orElseThrow(() -> new IllegalArgumentException("Challenge expired or invalid"));

        var secret = userDao.findTwoFactorSecretByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("2FA not enabled"));

        if (!totp.isValid(secret, Integer.parseInt(req.code()))) {
            throw new IllegalArgumentException("Invalid 2FA code");
        }

        var user = userDao.findPublicByEmail(email).orElseThrow();
        return LoginResponseDTO.token(user, issueToken(user), true);
    }

    @Override
    @Transactional
    public String enable2faForUser(String email) {
        var normalized = email.toLowerCase();
        var secret = totp.newSecret();

        // Store the secret but DO NOT enable yet
        userDao.storeTwoFactorSecret(normalized, secret, false);

        // Return otpauth:// URI for QR generation
        return totp.otpauthUri("RedbirdWorkout", normalized, secret);
    }


    @Override
    @Transactional
    public void confirm2faSetup(String email, String code) {
        var normalized = email.toLowerCase();

        var secretOpt = userDao.findTwoFactorSecretByEmail(normalized);
        if (secretOpt.isEmpty()) {
            throw new IllegalStateException("No pending 2FA secret for user " + normalized);
        }

        var secret = secretOpt.get();

        if (code == null || !code.matches("\\d{6}")) {
            throw new IllegalArgumentException("Invalid 2FA code format");
        }

        int numericCode = Integer.parseInt(code);

        if (!totp.isValid(secret, numericCode)) {
            throw new IllegalArgumentException("Invalid 2FA code");
        }

        // Only now mark 2FA as enabled for this user
        userDao.storeTwoFactorSecret(normalized, secret, true);
    }

}
