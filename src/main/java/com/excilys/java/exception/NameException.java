package com.excilys.java.exception;

public class NameException extends ComputerException {

	private static final long serialVersionUID = 1L;
	private String message;
	
	public NameException() {
		message = "The name is mandatory.";
	}

	public String getMessage() {
		return message;
	}
	
}
