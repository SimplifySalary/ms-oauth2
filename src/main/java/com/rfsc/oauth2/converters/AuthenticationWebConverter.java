package com.rfsc.oauth2.converters;

import com.rfsc.oauth2.model.AuthenticationWebRequest;
import org.springframework.stereotype.Component;

/**
 * @author biandra
 */
@Component
public class AuthenticationWebConverter {

    public static final String CHANNEL = "web";

    //TODO: revisar...
    public AuthenticationWebRequest convert(String rut, String code){
        AuthenticationWebRequest authenticationWebRequest = new AuthenticationWebRequest();
        authenticationWebRequest.setNombre_canal(CHANNEL);
        authenticationWebRequest.setRut(rut);
        authenticationWebRequest.setDigito_verificador("");
        authenticationWebRequest.setClave_x(code);
        authenticationWebRequest.setBin_tarjeta("");
        authenticationWebRequest.setLogo("");
        authenticationWebRequest.setIndicador_auto("");
        authenticationWebRequest.setOrigen_operacion("");
        authenticationWebRequest.setCod_comercio("");
        authenticationWebRequest.setNum_dpc("");
        authenticationWebRequest.setMonto_trx("");
        return authenticationWebRequest;
    }
}
