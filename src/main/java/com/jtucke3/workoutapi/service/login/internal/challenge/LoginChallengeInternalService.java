package com.jtucke3.workoutapi.service.login.internal.challenge;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LoginChallengeInternalService implements ILoginChallengeInternalService {

    private static class Ch {
        String email;
        Instant exp;
    }

    private final Map<String, Ch> map = new ConcurrentHashMap<>();
    private final Duration ttl = Duration.ofMinutes(5);

    @Override
    public String create(String email) {
        var id = UUID.randomUUID().toString();
        var c = new Ch();
        c.email = email;
        c.exp = Instant.now().plus(ttl);
        map.put(id, c);
        return id;
    }

    @Override
    public Optional<String> consume(String id) {
        var c = map.remove(id);
        if (c == null || Instant.now().isAfter(c.exp)) return Optional.empty();
        return Optional.of(c.email);
    }
}
