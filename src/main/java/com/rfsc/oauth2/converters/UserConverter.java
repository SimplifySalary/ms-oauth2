package com.rfsc.oauth2.converters;

import com.rfsc.oauth2.model.UserRequest;
import com.rfsc.oauth2.model.UserResponse;
import com.rfsc.oauth2.model.database.AuthorityEntity;
import com.rfsc.oauth2.model.database.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author biandra
 */
@Component
public class UserConverter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity convert(Long id, Set<AuthorityEntity> authorities, UserRequest user){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setAuthorities(authorities);
        return userEntity;
    }


    public UserResponse convert(UserEntity userEntity){
        UserResponse user = new UserResponse();
        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        return user;
    }
}
