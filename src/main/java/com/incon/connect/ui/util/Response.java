package com.incon.connect.ui.util;

import java.util.List;

public class Response {

	private int code;
	private boolean success;
	private String message;
	private List<?> data;
	private List<?> errors;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
	public List<?> getErrors() {
		return errors;
	}
	public void setErrors(List<?> errors) {
		this.errors = errors;
	}
}
