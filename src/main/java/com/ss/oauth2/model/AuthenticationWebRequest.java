package com.ss.oauth2.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author biandra
 */
@Data
public class AuthenticationWebRequest implements Serializable {

    private String nombre_canal;
    private String rut;
    private String digito_verificador;
    private String clave_x;
    private String bin_tarjeta;
    private String logo;
    private String indicador_auto;
    private String origen_operacion;
    private String cod_comercio;
    private String num_dpc;
    private String monto_trx;

}
