package com.rfsc.oauth2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * @author biandra
 */
@Service
public class CustomClientDetailsService implements ClientDetailsService {

    @Autowired
    private ControlledCacheService controlledCacheService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return controlledCacheService.get(clientId);
    }

}
