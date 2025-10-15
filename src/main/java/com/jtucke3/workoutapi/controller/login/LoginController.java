package com.jtucke3.workoutapi.controller.login;

import com.jtucke3.workoutapi.converter.login.AuthConv;
import com.jtucke3.workoutapi.dto.login.LoginRequestDTO;
import com.jtucke3.workoutapi.dto.login.LoginResponseDTO;
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
}
