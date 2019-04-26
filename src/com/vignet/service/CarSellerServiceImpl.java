package com.vignet.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.vignet.beans.SalesExecutiveBean;

public class CarSellerServiceImpl extends CarSellerAbstractService implements CarSellerService {
	// create SalesExecutiveBean class obj
	SalesExecutiveBean salesExecutiveBean = new SalesExecutiveBean();
	// create Scaaner object
	Scanner scn = null;

	// validate input price
	public double validatePrice(double price) {
		// create scanner object
		scn = new Scanner(System.in);
		if (price < 0) {
			System.out.println("Insert valid price");
			double validprice = scn.nextDouble();
			price = validprice;
			validatePrice(validprice);
		} // if
		return price;
	}// validatePrice method end

	// validate input field mobileNo Inner method
	public boolean validaeMobileInput(long mobileinput) {
		// convert long into string
		String mobileNo = Long.toString(mobileinput);
		Pattern pattern = Pattern.compile("[7-9]{1}[0-9]{9}");
		Matcher matcher = pattern.matcher(mobileNo);
		boolean validmobileNo = matcher.find();
		return validmobileNo;
	}

	// validate input field mobileNo outer method
	public Long validateMobile(long mobileNo) {
		while (!validaeMobileInput(mobileNo)) {
			scn = new Scanner(System.in);
			System.out.println("Insert valid mobile no");
			mobileNo = scn.nextLong();
		}
		return mobileNo;
	}

	// generate current system date
	public String generateDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	// validate input field AdharNo inner method
	public boolean validateAdharNumber(long adharNo) {
		String valiAadharNo = Long.toString(adharNo);

		if (valiAadharNo.length() > 12 || valiAadharNo.length() < 12) {
			return true;
		}
		return false;
	}

	// validate input field AdharNo outer method
	public Long validateAdharNo(long adharNo) {
		while (validateAdharNumber(adharNo)) {
			scn = new Scanner(System.in);
			System.out.println("Insert valid adhar No");
			adharNo = scn.nextLong();
		}
		return adharNo;
	}

	// validate input field PanNo inner method
	public boolean validatePanNo(String panNo) {
		// convert string into upercase
		String validPanNo = panNo.toUpperCase();

		String format = "[A-Z]{5}[0-9]{4}[A-Z]{1}";

		if (!validPanNo.matches(format)) {
			return true;
		}
		return false;
	}

	// validate input field PanNo outer method
	public String validPanNo(String panNo) {
		while (validatePanNo(panNo)) {
			scn = new Scanner(System.in);
			System.out.println("Your pan card no is invalid..Insert valid pan card no");
			System.out.println("pan card no should be 5 alphabets followed by 4 numbers followed by 1 alphabet");
			panNo = scn.nextLine();
		}
		return panNo;
	}

	// validate input field vinNo inner method
	public boolean validateVinNo(String vin) {

		if (vin.contains("l") || vin.contains("u") || vin.contains("o") || vin.length() > 8 || vin.length() < 8
				|| vin.trim().length() == 0) {
			return true;
		}
		return false;
	}

	// validate input field vinNo inner method
	public String validateVinNumber(String vin) {
		while (validateVinNo(vin)) {
			scn = new Scanner(System.in);
			System.out.println("Insert correct Veichle Identification Number(VIN)");
			vin = scn.nextLine();
		}
		return vin;
	}
}// class
