package com.rfsc.oauth2.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author biandra
 */
@Data
public class UserAuthorityRequest implements Serializable {

    @NotNull
    private Long id;

}
