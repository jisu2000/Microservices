package com.jisu.user.service.playload;

import org.springframework.http.HttpStatus;




public class ApiResponce {
	
	

	public ApiResponce() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	private boolean success;
	private HttpStatus status;
	
	
	
}
