package com.jtucke3.workoutapi.controller.friends;

import com.jtucke3.workoutapi.converter.friends.FriendConv;
import com.jtucke3.workoutapi.service.friends.external.IFriendExternalService;
import com.jtucke3.workoutapi.webVo.friends.FriendActivityWebVo;
import com.jtucke3.workoutapi.webVo.friends.FriendPreviewWebVo;
import com.jtucke3.workoutapi.webVo.friends.FriendProfileWebVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/friends")
@RequiredArgsConstructor
public class FriendsController {

    private final IFriendExternalService service;
    private final FriendConv conv;

    /**
     * Search for users to add as friends.
     * The current user id is passed as a query parameter (?userId=...).
     */
    @GetMapping("/search")
    public List<FriendPreviewWebVo> search(
            @RequestParam("userId") UUID userId,
            @RequestParam("q") String query
    ) {
        var dtos = service.searchUsers(userId, query);
        return conv.toWebVoList(dtos);
    }

    /**
     * List currently accepted friends for the given user.
     */
    @GetMapping
    public List<FriendPreviewWebVo> list(
            @RequestParam("userId") UUID userId
    ) {
        var dtos = service.listFriends(userId);
        return conv.toWebVoList(dtos);
    }

    /**
     * List incoming friend requests (other users who have sent requests to this user).
     */
    @GetMapping("/incoming")
    public List<FriendPreviewWebVo> incoming(
            @RequestParam("userId") UUID userId
    ) {
        var dtos = service.listIncomingRequests(userId);
        return conv.toWebVoList(dtos);
    }

    /**
     * Send a friend request to another user.
     * For public accounts, this may auto-accept and create a mutual friendship.
     */
    @PostMapping("/request/{targetUserId}")
    public void sendRequest(
            @RequestParam("userId") UUID userId,
            @PathVariable("targetUserId") UUID targetUserId
    ) {
        service.sendFriendRequest(userId, targetUserId);
    }

    /**
     * Accept a pending friend request from another user.
     */
    @PostMapping("/accept/{fromUserId}")
    public void acceptRequest(
            @RequestParam("userId") UUID userId,
            @PathVariable("fromUserId") UUID fromUserId
    ) {
        service.acceptFriendRequest(userId, fromUserId);
    }

    /**
     * Remove an existing friend (both directions).
     */
    @DeleteMapping("/{friendId}")
    public void removeFriend(
            @RequestParam("userId") UUID userId,
            @PathVariable("friendId") UUID friendId
    ) {
        service.removeFriend(userId, friendId);
    }

    /**
     * Friend profile: workouts/goals + privacy details.
     */
    @GetMapping("/profile/{friendId}")
    public FriendProfileWebVo getFriendProfile(
            @RequestParam("userId") UUID currentUserId,
            @PathVariable("friendId") UUID friendId
    ) {
        var dto = service.getFriendProfile(currentUserId, friendId);
        return conv.toProfileWebVo(dto);
    }

    /**
     * Placeholder for dashboard “friends activity”.
     */
    @GetMapping("/activity")
    public List<FriendActivityWebVo> recentActivity(
            @RequestParam("userId") UUID userId
    ) {
        var dtos = service.recentActivity(userId);
        return conv.toActivityWebVoList(dtos);
    }
}
