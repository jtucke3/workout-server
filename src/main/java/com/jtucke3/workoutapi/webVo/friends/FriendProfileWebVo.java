package com.jtucke3.workoutapi.webVo.friends;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Web-layer representation of a friend's profile summary.
 * Workouts/goals are kept as empty lists for now – you can flesh out
 * summary objects later and wire them into this class.
 */
public class FriendProfileWebVo {

    private UUID id;
    private String displayName;

    private boolean profilePrivate;
    private boolean isFriend;
    private boolean canViewDetails;

    private List<Object> workouts = Collections.emptyList();
    private List<Object> goals = Collections.emptyList();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isProfilePrivate() {
        return profilePrivate;
    }

    public void setProfilePrivate(boolean profilePrivate) {
        this.profilePrivate = profilePrivate;
    }

    public boolean isFriend() {
        return isFriend;
    }

    public void setFriend(boolean friend) {
        isFriend = friend;
    }

    public boolean isCanViewDetails() {
        return canViewDetails;
    }

    public void setCanViewDetails(boolean canViewDetails) {
        this.canViewDetails = canViewDetails;
    }

    public List<Object> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Object> workouts) {
        this.workouts = workouts;
    }

    public List<Object> getGoals() {
        return goals;
    }

    public void setGoals(List<Object> goals) {
        this.goals = goals;
    }
}
