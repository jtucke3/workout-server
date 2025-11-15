package com.jtucke3.workoutapi.service.login.internal.totp;

import dev.samstevens.totp.code.CodeGenerator;
import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.code.HashingAlgorithm;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.code.DefaultCodeVerifier;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class TotpInternalService implements ITotpInternalService {

    private final SecretGenerator secretGen = new DefaultSecretGenerator(32);
    private final DefaultCodeVerifier verifier;

    public TotpInternalService() {
        CodeGenerator codeGen = new DefaultCodeGenerator(HashingAlgorithm.SHA1);
        var timeProvider = new SystemTimeProvider();
        this.verifier = new DefaultCodeVerifier(codeGen, timeProvider);
        // optional: keep standard 30s, or customize:
        // verifier.setTimePeriod(30);
        verifier.setAllowedTimePeriodDiscrepancy(1); // accept ±1 step
    }

    @Override
    public String newSecret() {
        return secretGen.generate();
    }

    @Override
    public boolean isValid(String secret, String code) {
        CodeGenerator generator = new DefaultCodeGenerator(HashingAlgorithm.SHA1);
        SystemTimeProvider timeProvider = new SystemTimeProvider();
        DefaultCodeVerifier verifier = new DefaultCodeVerifier(generator, timeProvider);
        return verifier.isValidCode(secret, code);
    }

    @Override
    public String otpauthUri(String issuer, String account, String secret) {
        return String.format(
                "otpauth://totp/%s:%s?secret=%s&issuer=%s&algorithm=SHA1&digits=6&period=30",
                URLEncoder.encode(issuer, StandardCharsets.UTF_8),
                URLEncoder.encode(account, StandardCharsets.UTF_8),
                secret,
                URLEncoder.encode(issuer, StandardCharsets.UTF_8)
        );
    }
}
