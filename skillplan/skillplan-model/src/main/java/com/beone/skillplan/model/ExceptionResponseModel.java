package com.beone.skillplan.model;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ExceptionResponseModel {
	private String message;
	private Status status;

	public ExceptionResponseModel(String message, Status status) {
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Response.Status getStatus() {
		return status;
	}

	public void setStatus(Response.Status status) {
		this.status = status;
	}
}
