package com.jtucke3.workoutapi.service.login.internal.challenge;

import java.util.Optional;

public interface ILoginChallengeInternalService {
    String create(String email);
    Optional<String> consume(String challengeId);
}
