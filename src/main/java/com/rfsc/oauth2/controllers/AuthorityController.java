package com.rfsc.oauth2.controllers;

import com.rfsc.oauth2.exceptions.BindingResultException;
import com.rfsc.oauth2.model.Authority;
import com.rfsc.oauth2.services.AuthorityServices;
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
@RequestMapping("/api/authorities")
public class AuthorityController {

    @Autowired
    private AuthorityServices services;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Authority get(@PathVariable Long id) {
        return services.get(id);
    }

    //TODO: pages...
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Authority> getAll() {
        return services.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        services.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Authority create(@RequestBody @Validated Authority authority, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingResultException(bindingResult);
        }
        return services.create(authority);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Authority update(@RequestBody @Validated Authority authority, BindingResult bindingResult, @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
            throw new BindingResultException(bindingResult);
        }
        return services.update(id, authority);
    }

}
