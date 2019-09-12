package com.rfsc.oauth2.converters;

import com.rfsc.oauth2.model.Authority;
import com.rfsc.oauth2.model.database.AuthorityEntity;
import org.springframework.stereotype.Component;

/**
 * @author biandra
 */
@Component
public class AuthorityConverter {

    public AuthorityEntity convert(Authority authority){
        AuthorityEntity authorityEntity = new AuthorityEntity();
        authorityEntity.setId(authority.getId());
        authorityEntity.setName(authority.getName());
        return authorityEntity;
    }

    public Authority convert(AuthorityEntity authorityEntity){
        Authority authority = new Authority();
        authority.setId(authorityEntity.getId());
        authority.setName(authorityEntity.getName());
        return authority;
    }
}
