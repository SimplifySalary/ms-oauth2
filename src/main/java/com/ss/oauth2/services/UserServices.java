package com.ss.oauth2.services;

import com.ss.oauth2.converters.UserConverter;
import com.ss.oauth2.exceptions.ConstraintException;
import com.ss.oauth2.exceptions.IdNotExistException;
import com.ss.oauth2.model.UserRequest;
import com.ss.oauth2.model.UserResponse;
import com.ss.oauth2.model.database.UserEntity;
import com.ss.oauth2.repositories.CustomUserDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author biandra
 */
@Slf4j
@Service
public class UserServices {

    public static final String USER_DOESN_T_EXIST = "user doesn't exist";
    @Autowired
    private CustomUserDetailsRepository repository;

    @Autowired
    private UserConverter converter;

    public UserResponse create(UserRequest user){
        try {
            repository.save(converter.convert(null, null, user));
        } catch (DataIntegrityViolationException ex){
            log.error("create user {} error", user.getUsername(), ex);
            throw new ConstraintException(ex.getMessage());

        }
        return converter.convert(repository.findByUsername(user.getUsername()));
    }

    public UserResponse update(Long id, UserRequest user){
        UserEntity userEntity = repository.findById(id).orElseThrow(() -> new IdNotExistException(USER_DOESN_T_EXIST));
        repository.save(converter.convert(id, userEntity.getAuthorities(), user));
        return get(id);
    }

    public UserResponse get(Long id){
        return repository.findById(id).map(user -> converter.convert(user))
                .orElseThrow(() -> new IdNotExistException(USER_DOESN_T_EXIST));
    }

    public List<UserResponse> getAll(){
        return repository.findAll().stream().map(user -> converter.convert(user)).collect(Collectors.toList());
    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException ex){
            throw new IdNotExistException(USER_DOESN_T_EXIST);
        }
    }
}
