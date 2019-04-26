package com.vignet.users;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import com.vignet.beans.CustomerBean;
import com.vignet.errors.CarNotAvailableException;
import com.vignet.service.CarSellerService;
import com.vignet.service.CarSellerServiceImpl;
import com.vignet.service.CarSellerAbstractService;
import com.vignet.util.Constant;
import com.vignet.util.DBConnection;

public class Customer {

	// global decleration
	Connection con = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	Statement statement = null;
	BufferedReader inputs = null;
	CarSellerService carSelerService = null;
	CarSellerAbstractService carSellerabstractService = null;
	int count = 0;
	CustomerBean customerBean = null;

	// method for customer role
	public void customerRole() throws Exception {

		// create service class object
		carSelerService = new CarSellerServiceImpl();
		// create BufferReader class object
		inputs = new BufferedReader(new InputStreamReader(System.in));
		// create CustomerBean class object.
		customerBean = new CustomerBean();
		// create service class object
		carSellerabstractService = new CarSellerServiceImpl();

		do {

			System.out.println("Customer Portal");
			System.out.println("Chose option you want to insert");
			System.out.println("\t 1.View car details");
			System.out.println("\t 2.Purchase new car");
			System.out.println("\t 3.Genrate bill");
			System.out.println("\t 4.Back to dashboard");
			System.out.println("\t 5.Exit");

			int option = 0;
			try {
				option = Integer.parseInt(inputs.readLine());
			} catch (NumberFormatException e) {
				System.out.println("You have entered a wrong input.");
				System.exit(0);
			}

			switch (option) {
			// View car details start
			case 1:
				while (true) {
					System.out.println("Chose option by viewing car");
					System.out.println("\t 1.By manufacture company name");
					System.out.println("\t 2.By model name");
					System.out.println("\t 3.By price");
					System.out.println("\t 4.Go to customer portal");
					System.out.println("\t 5.Exit");

					int innerOption = 0;
					try {
						innerOption = Integer.parseInt(inputs.readLine());
					} catch (NumberFormatException e) {
						System.out.println("You have entered a wrong input.");
						System.exit(0);
					}

					switch (innerOption) {
					// view by manufacture company name start
					case 1:

						System.out.println("View car details by company manufacture name ");
						System.out.println("--------------------------------------------");

						System.out.println("Enter manufacture company name");
						customerBean.setCarManufacturer(carSellerabstractService.validateName(inputs.readLine()));

						try {
							// get connection
							con = DBConnection.getConnection();
							// create preparedStatement
							preparedStatement = con.prepareStatement(Constant.VIEW_CAR_DETAILS_BY_COMPANY_NAME);
							// set the values to the preparedStatement
							preparedStatement.setString(1, customerBean.getCarManufacturer());
							// execute query
							resultSet = (ResultSet) preparedStatement.executeQuery();

							// read data from database
							while (resultSet.next()) {

								// display car details
								System.out.println("Veichle Identification No :" + resultSet.getString(1));
								System.out.println("Car Manufacture Company Name :" + resultSet.getString(2));
								System.out.println("Car Model Name :" + resultSet.getString(3));
								System.out.println("Car Variant Type :" + resultSet.getString(4));
								System.out.println("Car Price :" + resultSet.getString(5));
								System.out.println("Car Available Status :" + resultSet.getString(6));
								System.out.println("---------------------------------------");
								System.out.println("");

							} // while
						} // try
						catch (SQLException e) {
							e.printStackTrace();
						} // catch
						finally {
							// close the object
							resultSet.close();
							preparedStatement.close();
							DBConnection.close(con);
						}
						break;
					// view by manufacture company name end
					case 2:
						// case 2 for view car details by model name

						System.out.println("View car details by company model name");
						customerBean.setCarModelName(carSellerabstractService.validateName(inputs.readLine()));

						try {
							// get connection
							con = DBConnection.getConnection();
							// create preparedStatement
							preparedStatement = con.prepareStatement(Constant.VIEW_CAR_DETAILS_BY_MODELNAME);
							// set the values to the preparedStatement
							preparedStatement.setString(1, customerBean.getCarModelName());
							// execute query
							resultSet = (ResultSet) preparedStatement.executeQuery();
							// read data from database
							while (resultSet.next()) {
								System.out.println("Veichle Identification No :" + resultSet.getString(1));
								System.out.println("Car Manufacture Company Name:" + resultSet.getString(2));
								System.out.println("Car Model Name :" + resultSet.getString(3));
								System.out.println("Car Variant Type :" + resultSet.getString(4));
								System.out.println("Car Price :" + resultSet.getString(5));
								System.out.println("Car Available Status :" + resultSet.getString(6));
								System.out.println("---------------------------------------");
								System.out.println("");
								
							} // while
						} // try
						catch (SQLException e) {
							e.printStackTrace();
						} // catch
						finally {
							resultSet.close();
							preparedStatement.close();
							DBConnection.close(con);
						}
						break;
					// view by model name end

					case 3:
						// case 3 for view car details by given price range
						System.out.println("View car details by given range");
						System.out.println("Enter starting range");
						customerBean
								.setStartRange(carSelerService.validatePrice(Double.parseDouble(inputs.readLine())));

						System.out.println("Enter ending range");
						customerBean.setEndRange(carSelerService.validatePrice(Double.parseDouble(inputs.readLine())));

						try {
							// get connection
							con = DBConnection.getConnection();
							// create PrepareStatement
							preparedStatement = con.prepareStatement(Constant.VIEW_CAR_DETAILS_BY_RANGE);
							// set the values to the preparedStatement
							preparedStatement.setDouble(1, customerBean.getStartRange());
							preparedStatement.setDouble(2, customerBean.getEndRange());
							// execute query
							resultSet = (ResultSet) preparedStatement.executeQuery();
							// read data from database
							while (resultSet.next()) {
								System.out.println("Veichle Identification No :" + resultSet.getString(1));
								System.out.println("Car Manufacture Company :" + resultSet.getString(2));
								System.out.println("Car Model Name :" + resultSet.getString(3));
								System.out.println("Car Variant Type :" + resultSet.getString(4));
								System.out.println("Car Price :" + resultSet.getString(5));
								System.out.println("Car Available Status :" + resultSet.getString(6));
								System.out.println("---------------------------------------");
								System.out.println("");

							} // while
							if (!resultSet.next()) {
								System.out.println("No car found by given price ");
							}
						} // try
						catch (SQLException e) {
							e.printStackTrace();
						} // catch
						finally {
							resultSet.close();
							preparedStatement.close();
							DBConnection.close(con);
						}
						break;
					// view by price end
					case 4:
						// case 4 for calls the customer portal
						customerRole();
						break;
					default:
						System.out.println("Please insert correct option");
					}// while end
				} // inner switch

			case 2:
				// case 2 for purchase new car car

				//Read values from console, validate and set to the SalesExecutiveBean class
				System.out.println("Enetr customer name");
				customerBean.setCustomerName(carSellerabstractService.validateName(inputs.readLine()));

				System.out.println("Enter mobile number");
				customerBean.setMolileNo(carSelerService.validateMobile(Long.parseLong(inputs.readLine())));

				System.out.println("Enter PAN (Permanent Account Number) no");
				customerBean.setPanNo(carSelerService.validPanNo(inputs.readLine()));

				System.out.println("Enter adhar number");
				customerBean.setAdharNo(carSelerService.validateAdharNo(Long.parseLong(inputs.readLine())));

				System.out.println("Insert VIN (Veichle Identification Number) no");
				customerBean.setVinNumber(carSellerabstractService.validateVinNumber(inputs.readLine()));

				System.out.println("Enetr car model name");
				customerBean.setCarModelName(carSellerabstractService.validateName(inputs.readLine()));

				// get current system date
				String registerationDate = carSelerService.generateDate();
				customerBean.setRegisterationDate(registerationDate);

				try {
					// create connection
					con = DBConnection.getConnection();
					// create PreparedStatement
					preparedStatement = (PreparedStatement) con
							.prepareStatement(Constant.GET_CAR_PRICE_AND_AVAILABLE_STATUS);
					// set the value to preparedStatement
					preparedStatement.setString(1, customerBean.getVinNumber());
					// execute query
					resultSet = (ResultSet) preparedStatement.executeQuery();

					// for geting price from database
					while (resultSet.next()) {
						customerBean.setPrice(resultSet.getDouble(1));
						customerBean.setAvailabeStatus(resultSet.getString(2));
					}
					if (customerBean.getAvailabeStatus().equalsIgnoreCase("available")) {
						// create preparedStatement
						preparedStatement = con.prepareStatement(Constant.CUSTOMER_PURCHASE_CAR_DETAILS);

						// set the values to the preparedStatements
						preparedStatement.setString(1, customerBean.getCustomerName());
						preparedStatement.setLong(2, customerBean.getMolileNo());
						preparedStatement.setLong(3, customerBean.getAdharNo());
						preparedStatement.setString(4, customerBean.getPanNo());
						preparedStatement.setString(5, customerBean.getVinNumber());
						preparedStatement.setString(6, customerBean.getCarModelName());
						preparedStatement.setString(7, customerBean.getRegisterationDate());
						preparedStatement.setDouble(8, customerBean.getPrice());

						// execute query
						count = preparedStatement.executeUpdate();
						// check record inserted or not
						if (count != 0) {
							System.out.println("Your information submitted sucessfully!!!");
						}
					} // if
					else {
						throw new CarNotAvailableException();
					}
				} // try
				catch (SQLException e) {
					e.printStackTrace();
				} catch (InputMismatchException ime) {
					System.out.println("Wrong Input given");
				} // catch
				catch (NullPointerException npe) {
					System.out.println("Record Not Found.");
				} finally {
					resultSet.close();
					preparedStatement.close();
					DBConnection.close(con);
				}
				break;
			// purchase new Car Ends
			case 3:
				// case 3 for Generate Bill
				try {
					// get connection
					con = DBConnection.getConnection();
					// create statement obj
					statement = (Statement) con.createStatement();
					// exexute query
					resultSet = (ResultSet) statement.executeQuery(Constant.GENRATE_BILL);
					System.out.println("Bill Generated");
					while (resultSet.next()) {
						customerBean.setCustomerName(resultSet.getString(1));
						customerBean.setMolileNo(resultSet.getLong(2));
						customerBean.setAdharNo(resultSet.getLong(3));
						customerBean.setPanNo(resultSet.getString(4));
						customerBean.setVinNumber(resultSet.getString(5));
						customerBean.setCarModelName(resultSet.getString(6));
						customerBean.setRegisterationDate(resultSet.getString(7));
						customerBean.setPrice(resultSet.getDouble(8));
					}
					// display customer information
					System.out.println("Customer Details");
					System.out.println("Cotomer Name: " + customerBean.getCustomerName());
					System.out.println("Contact Number: " + customerBean.getMolileNo());
					System.out.println("Adhar Number: " + customerBean.getAdharNo());
					System.out.println("Personal Identification Number(PAN) No: " + customerBean.getPanNo());
					System.out.println("Veichle Identification No :" + customerBean.getVinNumber());
					System.out.println("Car Model Name :" + customerBean.getCarModelName());
					System.out.println("Registration Date :" + customerBean.getRegisterationDate());
					System.out.println("Price :" + customerBean.getPrice());
					System.out.println("---------------------------------");
					System.out.println("");

					// create preparedStatement
					preparedStatement = con.prepareStatement(Constant.UPDATE_CAR_AVAILABLE_STATUS);
					String availableStatus = "SOLD";
					// set the values to the preparedStatements
					preparedStatement.setString(1, availableStatus);
					preparedStatement.setString(2, customerBean.getVinNumber());
					preparedStatement.setString(3, customerBean.getCarModelName());
					// execute query
					count = preparedStatement.executeUpdate();
					// check record inserted or not
					if (count == 0) {
						System.out.println("Bill Not Generated");
					}
				} // try
				catch (SQLException e) {
					e.printStackTrace();
				} catch (InputMismatchException ime) {
					System.out.println("You have entered a wrong input.");
				} // catch
				finally {
					resultSet.close();
					statement.close();
					DBConnection.close(con);
				}
				break;
			case 4:
				// Case 4 for go to main Dashboard
				Main.carUsersRole();
				break;
			case 5:
				// Exit
				System.out.println("Thanks For Visiting Have a Nice Day !!!");
				System.exit(0);
				break;
			default:
				System.out.println("please insert correct option");
			}// outer switch end
		} while (true);
		// close BufferedReader object
	}// Customer constructor end
}// Customer class end
