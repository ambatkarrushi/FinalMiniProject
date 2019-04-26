package com.vignet.users;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import com.vignet.beans.SalesExecutiveBean;
import com.vignet.errors.InvalidChoiceException;
import com.vignet.service.CarSellerService;
import com.vignet.service.CarSellerServiceImpl;
import com.vignet.service.CarSellerAbstractService;
import com.vignet.util.Constant;
import com.vignet.util.DBConnection;

public class SalesExecutive {

	// global decleration
	Connection con = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	Statement statement = null;
	BufferedReader inputs = null;
	CarSellerService carSellerService = null;
	SalesExecutiveBean salesExecutiveBean = null;
	CarSellerAbstractService carSellerabstractService = null;
	int count = 0;

	public void salesExecutiveRole() throws Exception {

		// create BufferReader class object
		inputs = new BufferedReader(new InputStreamReader(System.in));

		// create service class object
		carSellerService = new CarSellerServiceImpl();

		// create SalesExecutiveBeans class obj
		salesExecutiveBean = new SalesExecutiveBean();

		// create service class object
		carSellerabstractService = new CarSellerServiceImpl();

		while (true) {

			System.out.println("\t 1. Add car");
			System.out.println("\t 2. View car");
			System.out.println("\t 3. Sold car");
			System.out.println("\t 4. Available car");
			System.out.println("\t 5. Back to dashboard");
			System.out.println("\t 6. Exit");

			System.out.println("Choose the option");
			int option = 0;
			try {
				option = Integer.parseInt(inputs.readLine());
			} catch (NumberFormatException e) {
				System.out.println("You have entered a wrong input.");
				System.exit(0);
			}
			switch (option) {
			case 1:

				try {
					System.out.println("Enter Car Details");
					System.out.println("......................");

					// Read values from console, validate and set to the SalesExecutiveBean class

					System.out.println("Insert Veichle Identification Number(VIN)");
					salesExecutiveBean.setVinNumber(carSellerabstractService.validateVinNumber(inputs.readLine()));

					System.out.println("Enter car manufacture company");
					salesExecutiveBean.setCarManufacturer(carSellerabstractService.validateName(inputs.readLine()));

					System.out.println("Enter the car modelName");
					salesExecutiveBean.setCarModelName(carSellerabstractService.validateName(inputs.readLine()));

					System.out.println("insert the car varient Type (Petrol/Disel)");
					salesExecutiveBean.setVarient(carSellerabstractService.validateName(inputs.readLine()));

					System.out.println("Price of the car");
					salesExecutiveBean.setPrice(carSellerService.validatePrice(Double.parseDouble(inputs.readLine())));

					String status = "AVAILABLE";

					// set the availability of the car
					salesExecutiveBean.setAvailabeStatus(status);

					// get connection
					con = DBConnection.getConnection();
					// create preparedStatement
					preparedStatement = con.prepareStatement(Constant.INSERT_CAR_DETAILS);
					// set the values to the preparedStatements
					preparedStatement.setString(1, salesExecutiveBean.getVinNumber());
					preparedStatement.setString(2, salesExecutiveBean.getCarManufacturer());
					preparedStatement.setString(3, salesExecutiveBean.getCarModelName());
					preparedStatement.setString(4, salesExecutiveBean.getVarient());
					preparedStatement.setDouble(5, salesExecutiveBean.getPrice());
					preparedStatement.setString(6, salesExecutiveBean.getAvailabeStatus());
					// execute query
					count = preparedStatement.executeUpdate();
					// check record inserted or not
					if (count != 0) {
						System.out.println("Car details sucessfully inserted");
					}

				} catch (SQLException e) {
					e.printStackTrace();
				} catch (InputMismatchException inputMistmatchException) {
					System.out.println("Wrong input given");
				} // catch
				finally {
					// close preparedstatement object
					preparedStatement.close();
					// close connection
					DBConnection.close(con);
				}
				break;
			case 2:
				// case 2 for add new car

				try {
					// crate arrayList object with bean class
					ArrayList<SalesExecutiveBean> carDetailsArrayList = new ArrayList<>();
					// get connection
					con = DBConnection.getConnection();
					// create statement
					statement = (Statement) con.createStatement();
					// execute query
					resultSet = (ResultSet) statement.executeQuery(Constant.VIEW_CAR_DETAILS);

					// read data from database
					while (resultSet.next()) {

						salesExecutiveBean.setVinNumber(resultSet.getString(1));
						salesExecutiveBean.setCarManufacturer(resultSet.getString(2));
						salesExecutiveBean.setCarModelName(resultSet.getString(3));
						salesExecutiveBean.setVarient(resultSet.getString(4));
						salesExecutiveBean.setPrice(resultSet.getDouble(5));
						salesExecutiveBean.setAvailabeStatus(resultSet.getString(6));

						// add SalesExecutiveBean class object to the ArrayList
						carDetailsArrayList.add(salesExecutiveBean);

						// check size of ArrayList
						System.out.println(carDetailsArrayList.size());

						// retrieving data from ArrayList
						Iterator<SalesExecutiveBean> carDetailsiterator = carDetailsArrayList.iterator();

						// check elements available in ArrayList or not
						while (carDetailsiterator.hasNext()) {
							salesExecutiveBean = carDetailsiterator.next();
						} // while

						// display car details
						System.out.println("Veichle Identification No :" + salesExecutiveBean.getVinNumber());
						System.out.println("Car Manufacture Company Name :" + salesExecutiveBean.getCarManufacturer());
						System.out.println("Car Model Name :" + salesExecutiveBean.getCarModelName());
						System.out.println("Car Variant Type :" + salesExecutiveBean.getVarient());
						System.out.println("Car Price :" + salesExecutiveBean.getPrice());
						System.out.println("Car Available Status :" + salesExecutiveBean.getAvailabeStatus());
						System.out.println("---------------------------------------");
						System.out.println("");
					} // while
				} // try
				catch (SQLException e) {
					e.printStackTrace();
				} // catch
				finally {
					resultSet.close();
					statement.close();
					DBConnection.close(con);
				}
				break;
			case 3:
				// case 3 for View sold car
				try {
					// get connection
					con = DBConnection.getConnection();
					// create PreparedStatement
					preparedStatement = (PreparedStatement) con.prepareStatement(Constant.VIEW_SOLD_CARS);
					// set the value to preparedStatement
					String updateStatus = "sold";
					preparedStatement.setString(1, updateStatus);
					// execute query
					resultSet = (ResultSet) preparedStatement.executeQuery();

					System.out.println("Sold Cars");
					System.out.println("");
					while (resultSet.next()) {
						// display Available car details
						System.out.println("Veichle Identification No :" + resultSet.getString(1));
						System.out.println("Car Manufacture Company :" + resultSet.getString(2));
						System.out.println("Car Model Name :" + resultSet.getString(3));
						System.out.println("Car Variant Type :" + resultSet.getString(4));
						System.out.println("Car Price :" + resultSet.getDouble(5));
						System.out.println("---------------------------------------");
						System.out.println("");
					}
				} // try
				catch (Exception e) {
					e.printStackTrace();
				} // catch
				finally {
					resultSet.close();
					preparedStatement.close();
					DBConnection.close(con);
				}
				break;
			case 4:
				// case 4 for View Available car
				try {
					// get Connection obj
					con = DBConnection.getConnection();
					// create PreparedStatement
					preparedStatement = (PreparedStatement) con.prepareStatement(Constant.VIEW_AVAILABLE_CARS);
					// set the value to preparedStatement
					String satatusAvailable = "AVAILABLE";
					preparedStatement.setString(1, satatusAvailable);
					// execute query
					resultSet = (ResultSet) preparedStatement.executeQuery();
					System.out.println("Available Cars ");
					System.out.println(" ");
					while (resultSet.next()) {
						// display Available car details
						System.out.println("Veichle Identification No :" + resultSet.getString(1));
						System.out.println("Car Manufacture Company :" + resultSet.getString(2));
						System.out.println("Car Model Name :" + resultSet.getString(3));
						System.out.println("Car Variant Type :" + resultSet.getString(4));
						System.out.println("Car Price :" + resultSet.getDouble(5));
						System.out.println("---------------------------------------");
						System.out.println("");
					}
				} // try
				catch (Exception e) {
					e.printStackTrace();
				} // catch
				finally {
					preparedStatement.close();
					resultSet.close();
					// close connection object
					DBConnection.close(con);
				}
				break;
			case 5:
				// case 5 for Back to main dashboard
				try {
					Main.carUsersRole();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 6:
				// case 6 for Exit
				System.out.println("Thanks For Visiting Have a Nice Day !!!");
				System.exit(0);
				break;

			default:
				// default for invalid option
				try {
					throw new InvalidChoiceException();
				} // try
				catch (Exception e) {
					System.out.println("please insert valid input");
				} // catch
				finally {
					inputs.close();
				}
			}// switch
		} // while
	}// method
}// class