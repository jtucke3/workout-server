package com.jtucke3.workoutapi.controller.login;

import com.jtucke3.workoutapi.converter.login.AuthConv;
import com.jtucke3.workoutapi.dto.login.LoginRequestDTO;
import com.jtucke3.workoutapi.dto.login.LoginResponseDTO;
import com.jtucke3.workoutapi.dto.login.RegisterRequestDTO;
import com.jtucke3.workoutapi.dto.login.Verify2FARequestDTO;
import com.jtucke3.workoutapi.dto.user.UserDTO;
import com.jtucke3.workoutapi.service.login.external.IAuthExternalService;
import com.jtucke3.workoutapi.webVo.login.*;
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
    public LoginResponseDTO verify(@RequestBody Verify2FAWebRequestWebVo webReq) {
        Verify2FARequestDTO req = conv.toDto(webReq);
        return service.verify2fa(req);
    }

    @PostMapping("/register")
    public UserDTO register(@RequestBody RegisterWebRequestWebVo webReq) {
        RegisterRequestDTO req = conv.toDto(webReq);
        return service.register(req);
    }

    // testing endpoint
    // @PostMapping("/2fa/enable")
    // public Enable2FAResponseDTO enable(@RequestParam("email") String email) {
    //     return service.enable2faForCurrentUserWithQr(email);
    // }
}
