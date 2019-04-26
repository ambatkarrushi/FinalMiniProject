package com.vignet.service;

public interface CarSellerService {

	public double validatePrice(double price);

	public String generateDate();

	public Long validateMobile(long mobileNo);

	public String validPanNo(String panNo);

	public Long validateAdharNo(long adharNo);
}
