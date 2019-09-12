package com.rfsc.oauth2.converters;

import com.rfsc.oauth2.model.database.ClientDetailsEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author biandra
 */
@Component
public class ClientDetailsConverter {

    public ClientDetails convert(ClientDetailsEntity customClientDetails){
        BaseClientDetails clientDetails = new BaseClientDetails();
        clientDetails.setClientId(customClientDetails.getClient_id());
        clientDetails.setScope(StringUtils.commaDelimitedListToSet(customClientDetails.getScope()));
        clientDetails.setAuthorizedGrantTypes(StringUtils.commaDelimitedListToSet(customClientDetails.getAuthorized_grant_types()));
        clientDetails.setAuthorities(AuthorityUtils.createAuthorityList(StringUtils.commaDelimitedListToStringArray(customClientDetails.getAuthorities())));
        clientDetails.setAccessTokenValiditySeconds(customClientDetails.getAccess_token_validity());
        clientDetails.setClientSecret(customClientDetails.getClient_secret());
        clientDetails.setAccessTokenValiditySeconds(customClientDetails.getAccess_token_validity());
        clientDetails.setRegisteredRedirectUri(StringUtils.commaDelimitedListToSet(customClientDetails.getWeb_server_redirect_uri()));
        clientDetails.setResourceIds(StringUtils.commaDelimitedListToSet(customClientDetails.getResource_ids()));
        return clientDetails;

    }
}
