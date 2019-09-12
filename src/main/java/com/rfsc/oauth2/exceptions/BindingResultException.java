package com.rfsc.oauth2.exceptions;

import org.springframework.validation.BindingResult;

public class BindingResultException extends RuntimeException {

    private BindingResult bindingResult;

    public BindingResultException(BindingResult bindingResult) {
        super("Request validations failed");
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }

    public void setBindingResult(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }
}
