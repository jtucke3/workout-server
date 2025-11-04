package com.jtucke3.workoutapi.controller.login;

import com.jtucke3.workoutapi.converter.login.AuthConv;
import com.jtucke3.workoutapi.dto.login.LoginRequestDTO;
import com.jtucke3.workoutapi.dto.login.LoginResponseDTO;
import com.jtucke3.workoutapi.dto.login.RegisterRequestDTO;
import com.jtucke3.workoutapi.dto.login.Verify2FARequestDTO;
import com.jtucke3.workoutapi.dto.user.UserDTO;
import com.jtucke3.workoutapi.service.login.external.IAuthExternalService;
import com.jtucke3.workoutapi.webVo.login.LoginWebRequestWebVo;
import com.jtucke3.workoutapi.webVo.login.LoginWebResponseWebVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginController {

    private final IAuthExternalService service;
    private final AuthConv conv;

    @PostMapping("/login")
    public LoginWebResponseWebVo login(@RequestBody LoginWebRequestWebVo webReq) {
        LoginRequestDTO dtoReq = conv.toDto(webReq);
        LoginResponseDTO dtoRes = service.login(dtoReq);
        return conv.toWeb(dtoRes);
    }

    @PostMapping("/2fa/verify")
    public LoginResponseDTO verify(@RequestBody Verify2FARequestDTO req) {
        return service.verify2fa(req);
    }

    @PostMapping("/register")
    public UserDTO register(@RequestBody RegisterRequestDTO req) {
        return service.register(req);
    }

    @PostMapping("/2fa/enable")
    public String enable2faForEmail(@RequestParam("email") String email) {
        // returns an otpauth:// URI you can paste/QR into Google Authenticator
        return service.enable2faForCurrentUser(email);
    }
}
