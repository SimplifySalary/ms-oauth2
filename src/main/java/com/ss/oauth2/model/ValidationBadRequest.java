package com.ss.oauth2.model;

import lombok.Data;

import java.util.List;

@Data
public class ValidationBadRequest extends ErrorResponse {

	private List<FieldValidationErrorResponse> fields;

	public ValidationBadRequest(String message, List<FieldValidationErrorResponse> fields) {
		super(message);
		this.fields = fields;
	}

}
