package com.ss.oauth2.controllers;

import com.ss.oauth2.exceptions.BindingResultException;
import com.ss.oauth2.model.Client;
import com.ss.oauth2.services.ClientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author biandra
 */
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientServices services;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client get(@PathVariable String id) {
        return services.get(id);
    }

    //TODO: pages...
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getAll() {
        return services.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        services.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@RequestBody @Validated Client client, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingResultException(bindingResult);
        }
        return services.create(client);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client update(@RequestBody @Validated Client client, BindingResult bindingResult, @PathVariable String id) {
        if (bindingResult.hasErrors()) {
            throw new BindingResultException(bindingResult);
        }
        return services.update(id, client);
    }

}
