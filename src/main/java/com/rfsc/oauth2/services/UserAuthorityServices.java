package com.rfsc.oauth2.services;

import com.rfsc.oauth2.converters.UserAuthorityConverter;
import com.rfsc.oauth2.exceptions.IdNotExistException;
import com.rfsc.oauth2.model.UserAuthorityRequest;
import com.rfsc.oauth2.model.UserAuthorityResponse;
import com.rfsc.oauth2.model.database.AuthorityEntity;
import com.rfsc.oauth2.model.database.UserEntity;
import com.rfsc.oauth2.repositories.AuthorityRepository;
import com.rfsc.oauth2.repositories.CustomUserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author biandra
 */
@Service
public class UserAuthorityServices {

    @Autowired
    private UserAuthorityConverter converter;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private CustomUserDetailsRepository repository;


    public UserAuthorityResponse create(Long userId, UserAuthorityRequest userAuthority){
        UserEntity user = repository.findById(userId).orElseThrow(() -> new IdNotExistException("user not exist"));
        AuthorityEntity autority = authorityRepository.findById(userAuthority.getId()).orElseThrow(() -> new IdNotExistException("authority not exist"));
        Set<AuthorityEntity> authorities = user.getAuthorities();
        authorities.add(autority);
        user.setAuthorities(authorities);
        repository.save(user);
        return converter.convert(autority) ;
    }

    public UserAuthorityResponse get(Long userId, Long authorityId){
        UserEntity user = repository.findById(userId).orElseThrow(() -> new IdNotExistException("userAuthority not exist"));
        Optional<AuthorityEntity> result = user.getAuthorities().stream().filter(authority -> authority.getId() == authorityId).findFirst();
        return result.map(ae -> converter.convert(ae)).orElseThrow(() -> new IdNotExistException("userAuthority not exist"));
    }

    public List<UserAuthorityResponse> getAll(Long userId){
        UserEntity user = repository.findById(userId).orElseThrow(() -> new IdNotExistException("userAuthority not exist"));
        return user.getAuthorities().stream().map(authorityEntity -> converter.convert(authorityEntity)).collect(Collectors.toList());
    }

    public void delete(Long userId, Long authorityId){
        UserEntity user = repository.findById(userId).orElseThrow(() -> new IdNotExistException("userAuthority not exist"));
        List<AuthorityEntity> authorities = user.getAuthorities().stream().filter(authority -> authority.getId() != authorityId).collect(Collectors.toList());
        user.setAuthorities(new HashSet<>(authorities));
        repository.save(user);
    }

}
