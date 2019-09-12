package com.rfsc.oauth2.services;

import com.rfsc.oauth2.converters.AuthorityConverter;
import com.rfsc.oauth2.exceptions.IdNotExistException;
import com.rfsc.oauth2.model.Authority;
import com.rfsc.oauth2.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author biandra
 */
@Service
public class AuthorityServices {

    @Autowired
    private AuthorityRepository repository;
    @Autowired
    private AuthorityConverter converter;

    public Authority create(Authority authority){
        repository.save(converter.convert(authority));
        return authority;
    }

    public Authority update(Long id, Authority authority){
        repository.findById(id).orElseThrow(() -> new IdNotExistException("authority not exist"));
        repository.save(converter.convert(authority));
        return authority;
    }

    public Authority get(Long id){
        return repository.findById(id).map(authority -> converter.convert(authority))
                .orElseThrow(() -> new IdNotExistException("authority not exist"));
    }

    public List<Authority> getAll(){
        return repository.findAll().stream().map(authority -> converter.convert(authority)).collect(Collectors.toList());
    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException ex){
            throw new IdNotExistException("authority not exist");
        }
    }
}
