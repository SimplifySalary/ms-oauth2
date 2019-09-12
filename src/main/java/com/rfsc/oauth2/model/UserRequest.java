package com.rfsc.oauth2.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author biandra
 */
@Data
public class UserRequest implements Serializable {

    @NotNull
    private String username;
    @NotNull
    private String password;
}
