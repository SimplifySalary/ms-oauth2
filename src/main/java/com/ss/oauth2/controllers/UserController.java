package com.ss.oauth2.controllers;

import com.ss.oauth2.exceptions.BindingResultException;
import com.ss.oauth2.model.UserRequest;
import com.ss.oauth2.model.UserResponse;
import com.ss.oauth2.services.UserServices;
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
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServices services;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse get(@PathVariable Long id) {
        return services.get(id);
    }

    //TODO: pages...
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAll() {
        return services.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        services.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody @Validated UserRequest user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingResultException(bindingResult);
        }
        return services.create(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse update(@RequestBody @Validated UserRequest user, BindingResult bindingResult, @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
            throw new BindingResultException(bindingResult);
        }
        return services.update(id, user);
    }

}
