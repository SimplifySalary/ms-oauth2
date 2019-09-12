package com.rfsc.oauth2.services;

import com.rfsc.oauth2.converters.ClientDetailsConverter;
import com.rfsc.oauth2.converters.UserDetailsConverter;
import com.rfsc.oauth2.repositories.CustomClientDetailsRepository;
import com.rfsc.oauth2.repositories.CustomUserDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author biandra
 */
@Slf4j
@Service
public class ControlledCacheService {

    @Autowired
    private CustomClientDetailsRepository customClientDetailsRepository;

    @Autowired
    private CustomUserDetailsRepository customUserDetailsRepository;

    @Autowired
    private ClientDetailsConverter clientDetailsConverter;

    @Autowired
    private UserDetailsConverter userDetailsConverter;


    @Cacheable(cacheNames = "cache_client", key = "T(com.rfsc.oauth2.services.ControlledCacheService).#clientId")
    public ClientDetails get(String clientId) throws ClientRegistrationException {
        return customClientDetailsRepository.findById(clientId).map(clint -> clientDetailsConverter.convert(clint)).orElseThrow(() -> {
            log.error("get clientId {} error", clientId);
            return new ClientRegistrationException("client doesn't exist " + clientId);
        });
    }

    @Cacheable(cacheNames = "cache_user", key = "T(com.rfsc.oauth2.services.ControlledCacheService).#username")
    public UserDetails getUser(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(customUserDetailsRepository.findByUsername(username)).map(user -> userDetailsConverter.convert(user)).orElseThrow(() -> {
            log.error("get username {} error", username);
            return new UsernameNotFoundException("username doesn't exist " + username);
        });
    }
}
