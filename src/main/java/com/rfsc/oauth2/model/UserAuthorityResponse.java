package com.rfsc.oauth2.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author biandra
 */
@Data
public class UserAuthorityResponse implements Serializable {

    private Long id;
    private String name;

}
