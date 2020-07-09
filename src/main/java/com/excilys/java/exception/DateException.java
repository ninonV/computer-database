package com.excilys.java.exception;

public class DateException extends ComputerException {

	private static final long serialVersionUID = 1L;
	private String message;

	public DateException() {
		message = "Introduced date must be before discontinued date.";
	}

	public String getMessage() {
		return message;
	}
}
