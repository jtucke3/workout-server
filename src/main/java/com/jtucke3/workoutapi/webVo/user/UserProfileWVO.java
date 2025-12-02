package com.jtucke3.workoutapi.webVo.user;

public class UserProfileWVO {
    private String username;
    private String email;
    private boolean profilePrivate;

    public UserProfileWVO() {}

    public UserProfileWVO(String username, String email, boolean profilePrivate) {
        this.username = username;
        this.email = email;
        this.profilePrivate = profilePrivate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isProfilePrivate() {
        return profilePrivate;
    }

    public void setProfilePrivate(boolean profilePrivate) {
        this.profilePrivate = profilePrivate;
    }
}
