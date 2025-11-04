package com.jtucke3.workoutapi.webVo.login;

public class Verify2FAWebRequestWebVo {
    private String challengeId;
    private String code; // keep as string so leading zeros aren't lost

    public String getChallengeId() { return challengeId; }
    public void setChallengeId(String challengeId) { this.challengeId = challengeId; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
}
