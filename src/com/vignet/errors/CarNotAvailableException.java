package com.vignet.errors;

public class CarNotAvailableException extends Exception {

	private static final long serialVersionUID = 1L;

	public CarNotAvailableException() {
		System.out.println("Car not available for sold please visit after some days");
	}

}
