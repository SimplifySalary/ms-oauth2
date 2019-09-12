package com.ss.oauth2.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author biandra
 */
@Data
public class UserResponse implements Serializable {

    private Long id;
    private String username;
    private String password;
    private Boolean accountExpired;
    private Boolean accountLocked;
    private Boolean credentialsExpired;
    private Boolean enabled;

}
