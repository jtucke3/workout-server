package com.jtucke3.workoutapi.controller.user;

import com.jtucke3.workoutapi.converter.user.UserConv;
import com.jtucke3.workoutapi.dto.user.ChangePasswordRequestDTO;
import com.jtucke3.workoutapi.dto.user.UserDTO;
import com.jtucke3.workoutapi.service.user.external.IUserAccountExternalService;
import com.jtucke3.workoutapi.webVo.user.UserProfileWVO;
import com.jtucke3.workoutapi.webVo.user.ChangePasswordWebRequestVo;
import com.jtucke3.workoutapi.webVo.general.BaseWebResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class UserAccountController {

    private final IUserAccountExternalService service;

    @PutMapping("/change-password")
    public BaseWebResponseVo changePassword(@RequestHeader("X-User-Id") UUID userId,
                                            @RequestBody ChangePasswordWebRequestVo webVo) {
        ChangePasswordRequestDTO dto = UserConv.changePasswordReuquestToDTO(userId, webVo);
        UserDTO _updated = service.changePassword(dto);

        BaseWebResponseVo response = new BaseWebResponseVo();
        response.setSuccess(true);
        response.setMessage("Password changed successfully");
        return response;
    }

        @PutMapping("/update-profile")
    public BaseWebResponseVo updateProfile(@RequestHeader("X-User-Id") UUID userId,
                                           @RequestBody UserProfileWVO wvo) {
        UserDTO updated = service.updateProfile(userId, wvo);

        BaseWebResponseVo response = new BaseWebResponseVo();
        response.setSuccess(true);
        response.setMessage("Profile updated successfully");
        return response;
    }

}
