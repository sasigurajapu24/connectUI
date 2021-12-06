package com.incon.connect.ui.exceptions;

public class ConnectCustomException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4287862636185591567L;

	public ConnectCustomException() {
	}

	public ConnectCustomException(String message) {
		super(message);
	}

	public ConnectCustomException(Throwable cause) {
		super(cause);
	}

	public ConnectCustomException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConnectCustomException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
