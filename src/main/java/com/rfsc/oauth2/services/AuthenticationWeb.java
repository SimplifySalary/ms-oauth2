package com.rfsc.oauth2.services;

import com.rfsc.oauth2.converters.AuthenticationWebConverter;
import com.rfsc.oauth2.model.AuthenticationWebRequest;
import com.rfsc.oauth2.model.AuthenticationWebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @author biandra
 */
@Slf4j
@Service
public class AuthenticationWeb {

    @Autowired
    private AuthenticationWebConverter convert;

    @Value("${mq.path.valida-clave-web:http://172.30.81.200:32100/queue/valida_clave_web}")
    private String bridgeApiBaseUrl;

    @Autowired
    private RestTemplate restTemplate;

    public AuthenticationWebResponse authenticate(String rut, String code) {
        AuthenticationWebRequest request = convert.convert(rut, code);
        ResponseEntity<AuthenticationWebResponse> responseEntity = restTemplate.exchange(bridgeApiBaseUrl,
                HttpMethod.POST,
                new HttpEntity<Object>(request, getHeaders()),
                AuthenticationWebResponse.class);

        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            log.error("authenticate failure responseEntity: {}", responseEntity);
            throw new RuntimeException("failure authentication..."); //TODO: granularizar exceptions de authenticacion dependiendo de errores del recurso que se consume.
        }

        return responseEntity.getBody();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json"));
        return headers;
    }
}
