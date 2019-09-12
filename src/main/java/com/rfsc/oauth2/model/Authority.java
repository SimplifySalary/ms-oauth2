package com.rfsc.oauth2.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author biandra
 */
@Data
public class Authority implements Serializable {

    @NotNull
    private Long id;
    @NotNull
    private String name;

}
