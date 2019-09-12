package com.rfsc.oauth2.converters;

import com.rfsc.oauth2.model.Client;
import com.rfsc.oauth2.model.database.ClientDetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author biandra
 */
@Component
public class ClientConverter {


    @Autowired
    private PasswordEncoder passwordEncoder;

    public ClientDetailsEntity convert(String id, Client client){
        ClientDetailsEntity customClientDetails = new ClientDetailsEntity();
        customClientDetails.setClient_id(id);
        customClientDetails.setResource_ids(client.getResource_ids());
        customClientDetails.setClient_secret(passwordEncoder.encode(client.getClient_secret()));
        customClientDetails.setScope(client.getScope());
        customClientDetails.setAuthorized_grant_types(client.getAuthorized_grant_types());
        customClientDetails.setWeb_server_redirect_uri(client.getWeb_server_redirect_uri());
        customClientDetails.setAuthorities(client.getAuthorities());
        customClientDetails.setAccess_token_validity(client.getAccess_token_validity());
        customClientDetails.setRefresh_token_validity(client.getRefresh_token_validity());
        customClientDetails.setAdditional_information(client.getAdditional_information());
        customClientDetails.setAutoapprove(client.getAutoapprove());
        return customClientDetails;
    }

    public Client convert(ClientDetailsEntity customClientDetails){
        Client client = new Client();
        client.setId(customClientDetails.getClient_id());
        client.setResource_ids(customClientDetails.getResource_ids());
        client.setClient_secret(customClientDetails.getClient_secret());
        client.setScope(customClientDetails.getScope());
        client.setAuthorized_grant_types(customClientDetails.getAuthorized_grant_types());
        client.setWeb_server_redirect_uri(customClientDetails.getWeb_server_redirect_uri());
        client.setAuthorities(customClientDetails.getAuthorities());
        client.setAccess_token_validity(customClientDetails.getAccess_token_validity());
        client.setRefresh_token_validity(customClientDetails.getRefresh_token_validity());
        client.setAdditional_information(customClientDetails.getAdditional_information());
        client.setAutoapprove(customClientDetails.getAutoapprove());
        return client;
    }
}
