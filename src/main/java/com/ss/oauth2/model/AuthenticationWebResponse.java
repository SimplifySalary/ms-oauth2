package com.ss.oauth2.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author biandra
 */
@Data
public class AuthenticationWebResponse implements Serializable {

    private Long codigo;
    private String mensaje;
    private String rut;
    private String indicador_clave;
    private String tipo_clave;
    private String exigencia_clave;

}
