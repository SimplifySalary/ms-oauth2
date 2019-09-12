package com.rfsc.oauth2.model;

import lombok.Data;

@Data
public class FieldValidationErrorResponse {

	private String name;
	private String message;

	public FieldValidationErrorResponse(String name, String message){
		this.name = name;
		this.message = message;
	}
}
