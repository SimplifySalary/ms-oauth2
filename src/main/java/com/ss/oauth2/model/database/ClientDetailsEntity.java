package com.ss.oauth2.model.database;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author biandra
 */
@Entity
@Data
@Table(name="oauth_client_details")
public class ClientDetailsEntity implements Serializable {

    @Id
    private String client_id;
    private String resource_ids;
    private String client_secret;
    private String scope;
    private String authorized_grant_types;
    private String web_server_redirect_uri;
    private String authorities;
    private Integer access_token_validity;
    private Integer refresh_token_validity;
    private String additional_information;
    private String autoapprove;

}
