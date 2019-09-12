package com.ss.oauth2.model;

public class BadRequest<T> extends ErrorResponse {

	private T payload;

	public BadRequest(String message, T payload) {
		super(message);
		this.payload = payload;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}
}
