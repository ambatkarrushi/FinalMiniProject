package com.vignet.errors;

public class InvalidChoiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidChoiceException() {
		System.out.println("Invalid Option selected");
	}
}
