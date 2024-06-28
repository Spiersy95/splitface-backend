package com.splitface.tattoo.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class PasswordUtilsTest {

    @Test
    void hashPassword() {
        PasswordUtils passwordUtils = new PasswordUtils();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String plainPassword = "shhrw56*&";
        String encreptPassword = passwordUtils.hashPassword(plainPassword);
        assertTrue(encoder.matches(plainPassword,encreptPassword));

        assertTrue(encoder.matches("ionION234&*","$2a$10$KhNFwXqr7jP22w2rvJII5ueEKIYbA/gHuhhRjJMwfSDS37uedBsMq"));
    }
}