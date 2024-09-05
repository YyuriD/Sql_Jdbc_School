package com.foxminded.yuri.school.service;

public class ServiceLayerException extends Exception {

	public ServiceLayerException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = -3904879336278432685L;

	public ServiceLayerException() {
		super();
	}

	public ServiceLayerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceLayerException(String message) {
		super(message);
	}

}
