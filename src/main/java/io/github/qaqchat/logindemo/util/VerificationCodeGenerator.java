package io.github.qaqchat.logindemo.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class VerificationCodeGenerator {
    private static final String DIGITS = "0123456789";
    private static final int length = 6;
    private static final SecureRandom RANDOM = new SecureRandom();

    public String generateCode() {
        StringBuilder code = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            code.append(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));
        }
        return code.toString();
    }
}
