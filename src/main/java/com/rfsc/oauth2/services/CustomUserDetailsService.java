package com.rfsc.oauth2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author biandra
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ControlledCacheService controlledCacheService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return controlledCacheService.getUser(username);
    }
}
