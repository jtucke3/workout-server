package com.jtucke3.workoutapi.webVo.user;

public class UserProfileWVO {
    private String username;
    private String email;

    public UserProfileWVO() {}

    public UserProfileWVO(String username, String email) {
        this.username = username;
        this.email = email;
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
}
