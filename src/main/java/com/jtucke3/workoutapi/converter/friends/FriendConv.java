package com.jtucke3.workoutapi.converter.friends;

import com.jtucke3.workoutapi.dto.friends.FriendActivityDTO;
import com.jtucke3.workoutapi.dto.friends.FriendPreviewDTO;
import com.jtucke3.workoutapi.webVo.friends.FriendActivityWebVo;
import com.jtucke3.workoutapi.webVo.friends.FriendPreviewWebVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FriendConv {

    public FriendPreviewWebVo toWebVo(FriendPreviewDTO dto) {
        FriendPreviewWebVo vo = new FriendPreviewWebVo();
        vo.setId(dto.id());
        vo.setDisplayName(dto.displayName());
        vo.setFriend(dto.isFriend());
        vo.setPending(dto.isPending());
        return vo;
    }

    public List<FriendPreviewWebVo> toWebVoList(List<FriendPreviewDTO> dtos) {
        return dtos.stream().map(this::toWebVo).toList();
    }

    public FriendActivityWebVo toWebVo(FriendActivityDTO dto) {
        FriendActivityWebVo vo = new FriendActivityWebVo();
        vo.setFriendId(dto.friendId());
        vo.setFriendName(dto.friendName());
        vo.setWorkoutName(dto.workoutName());
        vo.setOccurredAt(dto.occurredAt());
        return vo;
    }

    public List<FriendActivityWebVo> toActivityWebVoList(List<FriendActivityDTO> dtos) {
        return dtos.stream().map(this::toWebVo).toList();
    }
}
