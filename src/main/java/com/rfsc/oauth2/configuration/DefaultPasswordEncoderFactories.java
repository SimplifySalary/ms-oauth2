package com.rfsc.oauth2.configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author biandra
 */
public class DefaultPasswordEncoderFactories {

    public static final String BCRYPT = "bcrypt";

    public static PasswordEncoder createDelegatingPasswordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(BCRYPT, new BCryptPasswordEncoder());
        DelegatingPasswordEncoder delegatingPasswordEncoder = new DelegatingPasswordEncoder(BCRYPT, encoders);
        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder(10));
        return delegatingPasswordEncoder;
    }
}
