package com.vignet.service;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class CarSellerAbstractService {

	Scanner scn = null;

	// abstract metthod
	public abstract String validateVinNumber(String vin);

	// validate input name Inner method
	public boolean validateInput(String name) {
		Pattern pattern = Pattern.compile("[^A-Za-z\\s\\s]");
		Matcher matcher = pattern.matcher(name);
		boolean pattenFound = matcher.find();

		if (name.startsWith(" ")) {
			return true;
		}

		if (pattenFound == true) {
			return true;
		}
		return false;
	}

	// validate input name outer method
	public String validateName(String inputs) {
		while (validateInput(inputs)) {
			// create scanner object
			scn = new Scanner(System.in);
			System.out.println("Insert valid name");
			inputs = scn.nextLine();
		}
		return inputs;
	}
}
