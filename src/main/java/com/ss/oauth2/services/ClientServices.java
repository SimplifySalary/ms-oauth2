package com.ss.oauth2.services;

import com.ss.oauth2.converters.ClientConverter;
import com.ss.oauth2.exceptions.ConstraintException;
import com.ss.oauth2.exceptions.IdNotExistException;
import com.ss.oauth2.model.Client;
import com.ss.oauth2.repositories.CustomClientDetailsRepository;
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
public class ClientServices {

    @Autowired
    private CustomClientDetailsRepository repository;
    @Autowired
    private ClientConverter converter;

    public Client create(Client client){
        repository.findById(client.getId()).map(c -> {
            log.error("invalid id, exist client with id {}", client.getId());
            throw new ConstraintException("invalid id");
        });
        try {
            repository.save(converter.convert(client.getId(), client));
        } catch (DataIntegrityViolationException ex){
            log.error("create client {} error", client.getId(), ex);
            throw new ConstraintException(ex.getMessage());

        }
        return client;
    }

    public Client update(String id, Client client){
        repository.findById(id).orElseThrow(() -> new IdNotExistException("client not exist"));
        repository.save(converter.convert(id, client));
        return client;
    }

    public Client get(String id){
        return repository.findById(id).map(client -> converter.convert(client))
                .orElseThrow(() -> new IdNotExistException("client not exist"));
    }

    public List<Client> getAll(){
        return repository.findAll().stream().map(customClientDetails -> converter.convert(customClientDetails)).collect(Collectors.toList());
    }

    public void delete(String id){
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException ex){
            throw new IdNotExistException("client not exist");
        }
    }
}
