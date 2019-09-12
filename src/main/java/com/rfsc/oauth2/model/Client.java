package com.rfsc.oauth2.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author biandra
 */
@Data
public class Client implements Serializable {

    @NotNull
    private String id;
    private String resource_ids;
    @NotNull
    private String client_secret;
    @NotNull
    private String scope;
    @NotNull
    private String authorized_grant_types;
    private String web_server_redirect_uri;
    @NotNull
    private String authorities;
    @NotNull
    private Integer access_token_validity;
    private Integer refresh_token_validity;
    private String additional_information;
    private String autoapprove;

}
