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

import java.util.Map;

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

    /**
     * Begin 2FA setup for the given account.
     * Returns an otpauth:// URI that the frontend will turn into a QR code.
     *
     * For now we accept the email as a request parameter; in a future pass
     * you can secure this with the authenticated user from the JWT instead.
     */
    @PostMapping("/2fa/enable")
    public Map<String, String> enable2fa(@RequestParam("email") String email) {
        String otpauthUri = service.enable2faForCurrentUser(email);
        return Map.of("otpauthUri", otpauthUri);
    }
}
