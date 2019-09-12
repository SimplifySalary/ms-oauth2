package com.rfsc.oauth2.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author biandra
 */
@Data
public class TokenRequest implements Serializable {

    @NotNull
    private String grant_type;
    @NotNull
    private String client_id;
    @NotNull
    private String client_secret;
}
