package com.vignet.util;

public class Constant {

	public static String INSERT_CAR_DETAILS = "INSERT INTO CAR_DETAILS (VIN_NUMBER,MANUFACTURE_NAME,MODEL_NAME,VARIENT_TYPE,PRICE,AVAILABLE_STATUS) VALUES(?,?,?,?,?,?)";

	public static String VIEW_CAR_DETAILS = "SELECT VIN_NUMBER,MANUFACTURE_NAME,MODEL_NAME,VARIENT_TYPE,PRICE,AVAILABLE_STATUS FROM CAR_DETAILS";

	public static String VIEW_AVAILABLE_CARS = "SELECT VIN_NUMBER,MANUFACTURE_NAME,MODEL_NAME,VARIENT_TYPE,PRICE FROM CAR_DETAILS WHERE AVAILABLE_STATUS=?";

	public static String VIEW_SOLD_CARS = "SELECT VIN_NUMBER,MANUFACTURE_NAME,MODEL_NAME,VARIENT_TYPE,PRICE FROM CAR_DETAILS WHERE AVAILABLE_STATUS=?";

	public static String GET_CAR_PRICE_AND_AVAILABLE_STATUS = "SELECT PRICE,AVAILABLE_STATUS FROM CAR_DETAILS WHERE VIN_NUMBER=?";

	public static String CUSTOMER_PURCHASE_CAR_DETAILS = "INSERT INTO CUSTOMER_DETAILS(CUSTOMER_NAME,MOBILE_NO,ADHAR_NO,PAN_NO,VIN_NO,MODEL_NAME,REGISTRATION_DATE,PRICE) VALUES(?,?,?,?,?,?,?,?)";

	public static String VIEW_CAR_DETAILS_BY_COMPANY_NAME = "SELECT VIN_NUMBER,MANUFACTURE_NAME,MODEL_NAME,VARIENT_TYPE,PRICE,AVAILABLE_STATUS FROM CAR_DETAILS WHERE MANUFACTURE_NAME=?";

	public static String VIEW_CAR_DETAILS_BY_MODELNAME = "SELECT VIN_NUMBER,MANUFACTURE_NAME,MODEL_NAME,VARIENT_TYPE,PRICE,AVAILABLE_STATUS FROM CAR_DETAILS WHERE MODEL_NAME=?";

	public static String VIEW_CAR_DETAILS_BY_RANGE = "SELECT VIN_NUMBER,MANUFACTURE_NAME,MODEL_NAME,VARIENT_TYPE,PRICE,AVAILABLE_STATUS FROM CAR_DETAILS WHERE PRICE BETWEEN ? AND ?";

	public static String GENRATE_BILL = "SELECT CUSTOMER_NAME,MOBILE_NO,ADHAR_NO,PAN_NO,VIN_NO,MODEL_NAME,REGISTRATION_DATE,PRICE FROM CUSTOMER_DETAILS";

	public static String UPDATE_CAR_AVAILABLE_STATUS = "UPDATE CAR_DETAILS SET AVAILABLE_STATUS=? WHERE VIN_NUMBER =? AND MODEL_NAME=?";

}
