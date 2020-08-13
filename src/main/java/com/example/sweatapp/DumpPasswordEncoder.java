package com.example.sweatapp;

import org.springframework.security.crypto.password.PasswordEncoder;

public class DumpPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return String.format("secret: '%s'", rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return false;
    }
}
