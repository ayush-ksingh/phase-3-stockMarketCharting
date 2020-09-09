package com.sector.app.exception;

public class CustomResponse {

	private Integer status;
	private String errorMessage;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public CustomResponse(Integer status, String errorMessage) {
		super();
		this.status = status;
		this.errorMessage = errorMessage;
	}

}
