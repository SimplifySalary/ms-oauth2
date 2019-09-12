package com.rfsc.oauth2.converters;

import com.rfsc.oauth2.model.UserAuthorityResponse;
import com.rfsc.oauth2.model.database.AuthorityEntity;
import org.springframework.stereotype.Component;

/**
 * @author biandra
 */
@Component
public class UserAuthorityConverter {


    public UserAuthorityResponse convert(AuthorityEntity authorityEntity){
        UserAuthorityResponse userAuthority = new UserAuthorityResponse();
        userAuthority.setId(authorityEntity.getId());
        userAuthority.setName(authorityEntity.getName());
        return userAuthority;
    }
}
