package com.vignet.users;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import com.vignet.errors.InvalidChoiceException;
import com.vignet.users.Customer;
import com.vignet.users.SalesExecutive;

public class Main {
	public static void main(String[] args) throws Exception {
		carUsersRole();
	}// main
	
	// method for different user
	public static void carUsersRole() throws Exception {
		System.out.println("Car Sales System");

		// create BufferReader class object
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Select your role");
		System.out.println("1.Sales Executive");
		System.out.println("2.Customer");
		System.out.println("3.Exit");

		int role = 0;

		try {
			role = Integer.parseInt(read.readLine());
		} catch (NumberFormatException e) {
			System.out.println("You have entered a wrong input.");
			System.exit(0);
		}

		switch (role) {
		case 1:
			// create SalesExecutive class object
			SalesExecutive salesExecutive = new SalesExecutive();
			salesExecutive.salesExecutiveRole();
			break;

		case 2:
			// create Customer class object
			Customer customer = new Customer();
			customer.customerRole();
			break;

		case 3:
			// Exit
			System.out.println("Thanks For Visiting Have a Nice Day !!!");
			System.exit(0);
			break;

		default:
			throw new InvalidChoiceException();
		}// switch
			// close the BufferReader object
		read.close();
	}// carUsersRole method
}// class
