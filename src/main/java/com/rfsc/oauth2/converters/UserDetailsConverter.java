package com.rfsc.oauth2.converters;

import com.rfsc.oauth2.model.CustomUserDetails;
import com.rfsc.oauth2.model.database.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author biandra
 */
@Component
public class UserDetailsConverter {

    public UserDetails
    convert(UserEntity userEntity){
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUserName(userEntity.getUsername());
        customUserDetails.setPassword(userEntity.getPassword());
        Set<GrantedAuthority> authorities = new HashSet<>();
        userEntity.getAuthorities().stream().forEach(authority -> authorities.add(new SimpleGrantedAuthority(authority.getName())));
        customUserDetails.setGrantedAuthorities(authorities);
        return customUserDetails;

    }
}
