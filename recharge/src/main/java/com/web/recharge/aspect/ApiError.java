package com.recharge.aspect;


import org.springframework.http.HttpStatus;

public class ApiError {
	 
    private HttpStatus status;
    private String message;
    private String[] errors;
 
    public ApiError(HttpStatus status, String message, String[] errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
 
    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        this.errors = new String[1];
        this.errors[0]=error;
    }

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String[] getErrors() {
		return errors;
	}

	public void setErrors(String[] errors) {
		this.errors = errors;
	}
}