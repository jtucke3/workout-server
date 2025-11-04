package com.jtucke3.workoutapi.converter.login;

import org.springframework.stereotype.Component;
import com.jtucke3.workoutapi.webVo.login.*;
import com.jtucke3.workoutapi.dto.login.*;
import com.jtucke3.workoutapi.dto.user.UserDTO;

@Component
public class AuthConv {

    // Login request unchanged
    public LoginRequestDTO toDto(LoginWebRequestWebVo web) {
        return new LoginRequestDTO(norm(web.getEmail()), safe(web.getPassword()));
    }

    // Map ALL fields including 2FA flags
    public LoginWebResponseWebVo toWeb(LoginResponseDTO dto) {
        var web = new LoginWebResponseWebVo();
        web.setToken(dto.token());
        if (dto.user() != null) {
            web.setUserId(dto.user().id());
            web.setEmail(dto.user().email());
            web.setDisplayName(dto.user().displayName());
        }
        web.setRequires2FA(dto.requires2FA());
        web.setChallengeId(dto.challengeId());
        return web;
    }

    public RegisterRequestDTO toDto(RegisterWebRequestWebVo web) {
        return new RegisterRequestDTO(norm(web.getEmail()), safe(web.getPassword()), trim(web.getDisplayName()));
    }

    public Verify2FARequestDTO toDto(Verify2FAWebRequestWebVo web) {
        return new Verify2FARequestDTO(safe(web.getChallengeId()), safe(web.getCode()));
    }

    private static String norm(String s) { return s == null ? null : s.trim().toLowerCase(); }
    private static String trim(String s) { return s == null ? null : s.trim(); }
    private static String safe(String s) { return s == null ? null : s; }
}
