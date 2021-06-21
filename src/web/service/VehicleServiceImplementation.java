
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

import web.model.Vehicle;
import web.utility.CommonConstants;
import web.utility.CommonUtility;
import web.utility.JDBCUtility;
import web.utility.QueryUtility;


//import web.service.Connection;
//import web.service.VehicleServiceImplementation;
//import web.service.Logger;
//import web.service.PreparedStatement;
//import web.service.Statement;



public class VehicleServiceImplementation implements IVehicleService{
	
	
	/** Initialize logger */
	public static final Logger vehicleLog = Logger.getLogger(VehicleServiceImplementation.class.getName());

	private static Connection connection;

	private static Statement statement;

	static{
		//create table or drop if exist
		createVehicleTable();
	}

	private PreparedStatement preparedStatement;
	
	public static void createVehicleTable() {

		try {
			connection = JDBCUtility.getDBConnection();
			statement = connection.createStatement();
			// Drop table if already exists and as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtility.queryById(CommonConstants.QUERY_ID_DROP_TABLE_VEHICLE));
			// Create new vehicles table as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtility.queryById(CommonConstants.QUERY_ID_CREATE_TABLE_VEHICLE));

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			vehicleLog.log(Level.SEVERE, e.getMessage());
		}
	}

	@Override
	public void addVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		String vehicleID = CommonUtility.generateVehicleIds(getVehicleIDs());
		
		try {
			connection = JDBCUtility.getDBConnection();
			/*
			 * Query is available in vehicleQuery.xml file and use
			 * insert_vehicle key to extract value of it
			 */
			preparedStatement = connection
					.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_INSERT_VEHICLES));
			connection.setAutoCommit(false);
			
			//Generate vehicle IDs
			vehicle.setVehicleID(vehicleID);
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, vehicle.getVehicleID());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, vehicle.getName() );
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, vehicle.getvType());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, vehicle.gettType());
			preparedStatement.setBoolean(CommonConstants.COLUMN_INDEX_FIVE, vehicle.isAirConditioning());
			preparedStatement.setBoolean(CommonConstants.COLUMN_INDEX_SIX, true);
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_SEVEN, vehicle.getImageUrl());
			preparedStatement.setDouble(CommonConstants.COLUMN_INDEX_EIGHT, vehicle.getRatePerDay());
			// Add vehicle
			preparedStatement.execute();
			connection.commit();

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			vehicleLog.log(Level.SEVERE, e.getMessage());
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
				vehicleLog.log(Level.SEVERE, e.getMessage());
			}
		}
		
	}

	@Override
	public Vehicle getVehicleByID(String vehicleID) {
		// TODO Auto-generated method stub
		return actionOnVehicle(vehicleID).get(0);
	}
	
	/*@Override
	public ArrayList<Vehicle> getVehiclesByType(String vType) {
		return actionOnVehicle(vType).get(0);
	}*/

	@Override
	public ArrayList<Vehicle> getVehicle() {
		// TODO Auto-generated method stub
		return actionOnVehicle(null);
	}

	@Override
	public Vehicle updateVehicle(String vehicleID, Vehicle vehicle) {
		// TODO Auto-generated method stub
		if (vehicleID != null && !vehicleID.isEmpty()) {
			/*
			 * Update vehicle query will be retrieved from vehicleQuery.xml
			 */
			try {
				connection = JDBCUtility.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_UPDATE_VEHICLE));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, vehicle.getVehicleID());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, vehicle.getName());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, vehicle.getvType());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, vehicle.gettType());
				preparedStatement.setBoolean(CommonConstants.COLUMN_INDEX_FIVE, vehicle.isAirConditioning());
				preparedStatement.setBoolean(CommonConstants.COLUMN_INDEX_SIX, vehicle.isAvailability());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_SEVEN, vehicle.getImageUrl());
				preparedStatement.setDouble(CommonConstants.COLUMN_INDEX_EIGHT, vehicle.getRatePerDay());
				preparedStatement.executeUpdate();

			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				vehicleLog.log(Level.SEVERE, e.getMessage());
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
					vehicleLog.log(Level.SEVERE, e.getMessage());
				}
			}
		}
		// Get the updated vehicle
		return getVehicleByID(vehicleID);
	}
	
	private ArrayList<Vehicle> actionOnVehicle(String vehicleID) {

		ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
		try {
			connection = JDBCUtility.getDBConnection();
			/*
			 * Before fetching vehicle it checks whether vehicle ID is
			 * available
			 */
			if (vehicleID != null && !vehicleID.isEmpty()) {
				/*
				 * Get vehicle by ID query will be retrieved from
				 * vehicleQuery.xml
				 */
				preparedStatement = connection
						.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_GET_VEHICLE));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, vehicleID);
			}
			/*
			 * If vehicle ID is not provided for get vehicle option it display
			 * all vehicles
			 */
			else {
				preparedStatement = connection
						.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_ALL_VEHICLES));
			}
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Vehicle vehicle = new Vehicle();
				vehicle.setVehicleID(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				vehicle.setName(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				vehicle. setvType(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				vehicle.settType(resultSet.getString(CommonConstants.COLUMN_INDEX_FOUR));
				vehicle.setAirConditioning(resultSet.getBoolean(CommonConstants.COLUMN_INDEX_FIVE));
				vehicle.setAvailability(resultSet.getBoolean(CommonConstants.COLUMN_INDEX_SIX));
				vehicle.setImageUrl(resultSet.getString(CommonConstants.COLUMN_INDEX_SEVEN));
				vehicle.setRatePerDay(resultSet.getDouble(CommonConstants.COLUMN_INDEX_EIGHT));
				vehicleList.add(vehicle);
			}

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			vehicleLog.log(Level.SEVERE, e.getMessage());
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
				vehicleLog.log(Level.SEVERE, e.getMessage());
			}
		}
		return vehicleList;
	}

	
	@Override
	public void removeVehicle(String vehicleID) {
		// TODO Auto-generated method stub
		if (vehicleID != null && !vehicleID.isEmpty()) {
			/*
			 * Remove vehicle query will be retrieved from vehicleQuery.xml
			 */
			try {
				connection = JDBCUtility.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_REMOVE_VEHICLE));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, vehicleID);
				preparedStatement.executeUpdate();
			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				vehicleLog.log(Level.SEVERE, e.getMessage());
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
					vehicleLog.log(Level.SEVERE, e.getMessage());
				}
			}
		}
		
	}
	
	private ArrayList<String> getVehicleIDs(){
		
		ArrayList<String> arrayList = new ArrayList<String>();
		/*
		 * List of vehicle IDs will be retrieved from vehicleQuery.xml
		 */
		try {
			connection = JDBCUtility.getDBConnection();
			preparedStatement = connection
					.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_GET_VEHICLE_IDS));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				arrayList.add(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
			}
		} catch (SQLException | SAXException | IOException | ParserConfigurationException
				| ClassNotFoundException e) {
			vehicleLog.log(Level.SEVERE, e.getMessage());
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
				vehicleLog.log(Level.SEVERE, e.getMessage());
			}
		}
		return arrayList;
	}

	
}
