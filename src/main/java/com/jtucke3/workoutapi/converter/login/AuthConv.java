package com.jtucke3.workoutapi.converter.login;

import org.springframework.stereotype.Component;
import com.jtucke3.workoutapi.webVo.login.*;
import com.jtucke3.workoutapi.dto.login.*;

@Component
public class AuthConv {
    public LoginRequestDTO toDto(LoginWebRequestWebVo web) {
        return new LoginRequestDTO(web.getEmail(), web.getPassword());
    }
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
}
