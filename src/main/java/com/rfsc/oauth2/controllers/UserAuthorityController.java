package com.rfsc.oauth2.controllers;

import com.rfsc.oauth2.exceptions.BindingResultException;
import com.rfsc.oauth2.model.UserAuthorityRequest;
import com.rfsc.oauth2.model.UserAuthorityResponse;
import com.rfsc.oauth2.services.UserAuthorityServices;
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
@RequestMapping("/resource/users/{userId}/authorities")
public class UserAuthorityController {

    @Autowired
    private UserAuthorityServices services;

    @GetMapping("/{authorityId}")
    @ResponseStatus(HttpStatus.OK)
    public UserAuthorityResponse get(@PathVariable Long userId, @PathVariable Long authorityId) {
        return services.get(userId, authorityId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserAuthorityResponse> getAll(@PathVariable Long userId) {
        return services.getAll(userId);
    }

    @DeleteMapping("/{authorityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId, @PathVariable Long authorityId) {
        services.delete(userId, authorityId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserAuthorityResponse create(@PathVariable Long userId, @RequestBody @Validated UserAuthorityRequest userAuthority, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingResultException(bindingResult);
        }
        return services.create(userId, userAuthority);
    }

}
