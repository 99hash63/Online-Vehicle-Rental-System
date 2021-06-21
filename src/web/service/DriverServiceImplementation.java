
package web.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import web.model.Driver;
import web.utility.CommonConstants;
import web.utility.CommonUtility;
import web.utility.JDBCUtility;
import web.utility.QueryUtility;


public class DriverServiceImplementation implements IDriverService {

	public static final Logger driverLog = Logger.getLogger(DriverServiceImplementation.class.getName());

	private static Connection connection;

	private static Statement statement;

	static{
		//create table or drop if exist
		createDriverTable();
	}

	private PreparedStatement preparedStatement;

	/**
	 * This method initially drop existing Employees table in the database and
	 * recreate table structure to insert employee entries
	 * 
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws ClassNotFoundException
	 *             - Thrown when an application tries to load in a class through
	 *             its string name using
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error
	 * @throws NullPointerException
	 *             - Service is not available
	 * 
	 */
	public static void createDriverTable() {

		try {
			connection = JDBCUtility.getDBConnection();
			statement = connection.createStatement();
			// Drop table if already exists and as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtility.queryById(CommonConstants.QUERY_ID_DROP_TABLE_DRIVER));
			// Create new employees table as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtility.queryById(CommonConstants.QUERY_ID_CREATE_TABLE_DRIVER));

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			driverLog.log(Level.SEVERE, e.getMessage());
		}
	}

	
	@Override
	public void addDriver(Driver driver) {

		String driverID = CommonUtility.generateDriverIds(getDriverIDs());
		
		try {
			connection = JDBCUtility.getDBConnection();
			
			preparedStatement = connection
					.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_INSERT_DRIVERS));
			connection.setAutoCommit(false);
			
			
			driver.setDriverID(driverID);
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, driver.getDriverID());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, driver.getFirstName());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, driver.getLastName());
			preparedStatement.setInt(CommonConstants.COLUMN_INDEX_FOUR, driver.getAge());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, driver.getNIC());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_SIX, driver.getLicense());
			//preparedStatement.setInt(CommonConstants.COLUMN_INDEX_SEVEN, driver.getRating());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_SEVEN, driver.getSex());
			preparedStatement.setBoolean(CommonConstants.COLUMN_INDEX_EIGHT, driver.isAvailability());
			// Add drivers
			preparedStatement.execute();
			connection.commit();

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			driverLog.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				driverLog.log(Level.SEVERE, e.getMessage());
			}
		}
	}
	@Override
	public Driver getDriversByID(String driverID) {

		return actionOnDriver(driverID).get(0);
	}
	

	@Override
	public ArrayList<Driver> getDrivers() {
		
		return actionOnDriver(null);
	}

	
	@Override
	public void removeDriver(String driverID) {

		// Before deleting check whether employee ID is available
		if (driverID != null && !driverID.isEmpty()) {
			/*
			 * Remove employee query will be retrieved from EmployeeQuery.xml
			 */
			try {
				connection = JDBCUtility.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_REMOVE_DRIVER));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, driverID);
				preparedStatement.executeUpdate();
			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				driverLog.log(Level.SEVERE, e.getMessage());
			} finally {
				/*
				 * Close prepared statement and database connectivity at the end
				 * of transaction
				 */
				try {
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					driverLog.log(Level.SEVERE, e.getMessage());
				}
			}
		}
	}

	private ArrayList<Driver> actionOnDriver(String driverID) {

		ArrayList<Driver> driverList = new ArrayList<Driver>();
		try {
			connection = JDBCUtility.getDBConnection();
			
			if (driverID != null && !driverID.isEmpty()) {
				
				preparedStatement = connection
						.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_GET_DRIVER));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, driverID);
			}

			else {
				preparedStatement = connection
						.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_ALL_DRIVERS));
			}
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Driver driver = new Driver();
				driver.setDriverID(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				driver.setFirstName(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				driver.setLastName(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				driver.setAge(resultSet.getInt(CommonConstants.COLUMN_INDEX_FOUR));
				driver.setNIC(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
				driver.setLicense(resultSet.getString(CommonConstants.COLUMN_INDEX_SIX));
				driver.setSex(resultSet.getString(CommonConstants.COLUMN_INDEX_SEVEN));
				driver.setAvailability(resultSet.getBoolean(CommonConstants.COLUMN_INDEX_EIGHT));
				driverList.add(driver);
			}

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			driverLog.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				driverLog.log(Level.SEVERE, e.getMessage());
			}
		}
		return driverList;
	}


	@Override
	public Driver updateDriver(String driverID, Driver driver) {

	
		if (driverID != null && !driverID.isEmpty()) {
			
			try {
				connection = JDBCUtility.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_UPDATE_DRIVER));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, driver.getDriverID());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, driver.getFirstName());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, driver.getLastName());
				preparedStatement.setInt(CommonConstants.COLUMN_INDEX_FOUR, driver.getAge());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, driver.getNIC());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_SIX, driver.getLicense());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_SEVEN, driver.getSex());
				preparedStatement.setBoolean(CommonConstants.COLUMN_INDEX_EIGHT, driver.isAvailability());
				preparedStatement.executeUpdate();

			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				driverLog.log(Level.SEVERE, e.getMessage());
			} finally {
				/*
				 * Close prepared statement and database connectivity at the end
				 * of transaction
				 */
				try {
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					driverLog.log(Level.SEVERE, e.getMessage());
				}
			}
		}
	
		return getDriversByID(driverID);
	}
	
	
	private ArrayList<String> getDriverIDs(){
		
		ArrayList<String> arrayList = new ArrayList<String>();
		
		try {
			connection = JDBCUtility.getDBConnection();
			preparedStatement = connection
					.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_GET_DRIVER_IDS));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				arrayList.add(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
			}
		} catch (SQLException | SAXException | IOException | ParserConfigurationException
				| ClassNotFoundException e) {
			driverLog.log(Level.SEVERE, e.getMessage());
		} finally {
		
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				driverLog.log(Level.SEVERE, e.getMessage());
			}
		}
		return arrayList;
	}
	
}
