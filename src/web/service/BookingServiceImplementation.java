
package web.service;

import java.io.IOException;
import java.sql.Connection;
//import java.time.LocalDate;
//import java.time.LocalTime;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import web.model.Booking;
import web.utility.CommonConstants;
import web.utility.CommonUtility;
import web.utility.JDBCUtility;
import web.utility.QueryUtility;


public class BookingServiceImplementation implements IBookingService {

	/** Initialize logger */
	public static final Logger bookingLog = Logger.getLogger(BookingServiceImplementation.class.getName());

	private static Connection connection;

	private static Statement statement;

	static{
		//create table or drop if exist
		createBookingTable();
	}

	private PreparedStatement preparedStatement;

	/**
	 * This method initially drop existing Bookings table in the database and
	 * recreate table structure to insert booking entries
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
	public static void createBookingTable() {

		try {
			connection = JDBCUtility.getDBConnection();
			statement = connection.createStatement();
			// Drop table if already exists and as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtility.queryById(CommonConstants.QUERY_ID_DROP_TABLE_BOOKING));
			// Create new Booking table as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtility.queryById(CommonConstants.QUERY_ID_CREATE_TABLE_BOOKING));

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			bookingLog.log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 * Add set of bookings for as a batch from the selected Booking List
	 * 
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error.
	 * 
	 */
	@Override
	public void addBooking(Booking booking) {

		String bookingId = CommonUtility.generateBookingIds(getBookingIDs());
		
		try {
			connection = JDBCUtility.getDBConnection();
			/*
			 * Query is available in BookingQuery.xml file and use
			 * insert_booking key to extract value of it
			 */
			preparedStatement = connection
					.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_INSERT_BOOKINGS));
			connection.setAutoCommit(false);
			
			//Generate booking IDs
			booking.setBookingId(bookingId);
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, booking.getBookingId());
			preparedStatement.setDate(CommonConstants.COLUMN_INDEX_TWO, Date.valueOf(booking.getPickupDate()));
			preparedStatement.setTime(CommonConstants.COLUMN_INDEX_THREE, Time.valueOf(booking.getPickupTime()));
			preparedStatement.setDate(CommonConstants.COLUMN_INDEX_FOUR, Date.valueOf(booking.getDropOffDate()));
			preparedStatement.setTime(CommonConstants.COLUMN_INDEX_FIVE, Time.valueOf(booking.getDropOffTime()));
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_SIX, booking.getLocation());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_SEVEN, booking.getCustomerId());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_EIGHT, booking.getVehicleId());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_NINE, booking.getDriverId());
			preparedStatement.setDouble(CommonConstants.COLUMN_INDEX_TEN, booking.getExpense());
			preparedStatement.setDate(CommonConstants.COLUMN_INDEX_ELEVEN, Date.valueOf(booking.getAddedDate()));
			// Add booking
			preparedStatement.execute();
			connection.commit();

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			bookingLog.log(Level.SEVERE, e.getMessage());
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
				bookingLog.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	/**
	 * Booking details can be retrieved based on the provided booking ID
	 * 
	 * @param bookingID
	 *            - Booking details are filtered based on the ID
	 * 
	 * @see #actionOnBooking()
	 */
	@Override
	public Booking getBookingById(String bookingId) {

		return actionOnBooking(bookingId).get(0);
	}
	
	/**
	 * Booking details can be retrieved based on the provided customer ID
	 * 
	 * @param customerId
	 *            - Booking details are filtered based on the customer ID
	 * 
	 * @see #actionOnBooking()
	 */
	@Override
	public ArrayList<Booking> getBookingByCustomerId(String customerId) {

		return actionOnCustomerBooking(customerId);
	}
	
	/**
	 * Get all list of bookings
	 * 
	 * @return ArrayList<Booking> 
	 * 						- Array of booking list will be return
	 * 
	 * @see #actionOnBooking()
	 */
	@Override
	public ArrayList<Booking> getBookings() {
		
		return actionOnBooking(null);
	}

	/**
	 * This method delete an booking based on the provided ID
	 * 
	 * @param bookingID
	 *            - Delete booking according to the filtered booking details
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
	 *             - Indicates a serious configuration error.
	 * @throws NullPointerException
	 *             - Service is not available
	 */
	@Override
	public void removeBooking(String bookingId) {

		// Before deleting check whether booking ID is available
		if (bookingId != null && !bookingId.isEmpty()) {
			/*
			 * Remove booking query will be retrieved from BookingQuery.xml
			 */
			try {
				connection = JDBCUtility.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_REMOVE_BOOKING));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, bookingId);
				preparedStatement.executeUpdate();
			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				bookingLog.log(Level.SEVERE, e.getMessage());
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
					bookingLog.log(Level.SEVERE, e.getMessage());
				}
			}
		}
	}

	/**
	 * This performs GET booking by Id and Display all bookings
	 * 
	 * @param bookingId
	 *            ID of the booking to remove or select from the list

	 * @return ArrayList<Booking> Array of booking list will be return
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
	 *             - Indicates a serious configuration error.
	 * @throws NullPointerException
	 *             - Service is not available
	 * 
	 * @see #getBookings()
	 * @see #getBookingById(String)
	 */
	private ArrayList<Booking> actionOnBooking(String bookingId) {

		ArrayList<Booking> bookingList = new ArrayList<Booking>();
		try {
			connection = JDBCUtility.getDBConnection();
			/*
			 * Before fetching booking it checks whether booking Id is
			 * available
			 */
			if (bookingId != null && !bookingId.isEmpty()) {
				/*
				 * Get booking by Id query will be retrieved from
				 * BookingQuery.xml
				 */
				preparedStatement = connection
						.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_GET_BOOKING));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, bookingId);
			}
			/*
			 * If booking Id is not provided for get booking option it display
			 * all bookings
			 */
			else {
				preparedStatement = connection
						.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_ALL_BOOKINGS));
			}
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Booking booking = new Booking();
				booking.setBookingId(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				booking.setPickupDate(resultSet.getDate(CommonConstants.COLUMN_INDEX_TWO).toLocalDate());
				booking.setPickupTime(resultSet.getTime(CommonConstants.COLUMN_INDEX_THREE).toLocalTime());
				booking.setDropOffDate(resultSet.getDate(CommonConstants.COLUMN_INDEX_FOUR).toLocalDate());
				booking.setDropOffTime(resultSet.getTime(CommonConstants.COLUMN_INDEX_FIVE).toLocalTime());
				booking.setLocation(resultSet.getString(CommonConstants.COLUMN_INDEX_SIX));
				booking.setCustomerId(resultSet.getString(CommonConstants.COLUMN_INDEX_SEVEN));
				booking.setVehicleId(resultSet.getString(CommonConstants.COLUMN_INDEX_EIGHT));
				booking.setDriverId(resultSet.getString(CommonConstants.COLUMN_INDEX_NINE));
				booking.setExpense(resultSet.getDouble(CommonConstants.COLUMN_INDEX_TEN));
				booking.setAddedDate(resultSet.getDate(CommonConstants.COLUMN_INDEX_ELEVEN).toLocalDate());
				bookingList.add(booking);
			}

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			bookingLog.log(Level.SEVERE, e.getMessage());
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
				bookingLog.log(Level.SEVERE, e.getMessage());
			}
		}
		return bookingList;
	}
	
	/**
	 * This performs GET booking by customer Id and Display all bookings
	 * 
	 * @param customerId
	 *            ID of the customer to remove or select from the list

	 * @return ArrayList<Booking> Array of booking list will be return
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
	 *             - Indicates a serious configuration error.
	 * @throws NullPointerException
	 *             - Service is not available
	 * 
	 * @see #getBookingByCustomerId(String)
	 */
	private ArrayList<Booking> actionOnCustomerBooking(String customerId) {

		ArrayList<Booking> bookingList = new ArrayList<Booking>();
		try {
			connection = JDBCUtility.getDBConnection();
			/*
			 * Before fetching booking it checks whether customer Id is
			 * available
			 */
			if (customerId != null && !customerId.isEmpty()) {
				/*
				 * Get bookings by customer Id query will be retrieved from
				 * BookingQuery.xml
				 */
				preparedStatement = connection
						.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_GET_CUSTOMER_BOOKING));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_SEVEN, customerId);
			}
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Booking booking = new Booking();
				booking.setBookingId(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				booking.setPickupDate(resultSet.getDate(CommonConstants.COLUMN_INDEX_TWO).toLocalDate());
				booking.setPickupTime(resultSet.getTime(CommonConstants.COLUMN_INDEX_THREE).toLocalTime());
				booking.setDropOffDate(resultSet.getDate(CommonConstants.COLUMN_INDEX_FOUR).toLocalDate());
				booking.setDropOffTime(resultSet.getTime(CommonConstants.COLUMN_INDEX_FIVE).toLocalTime());
				booking.setLocation(resultSet.getString(CommonConstants.COLUMN_INDEX_SIX));
				booking.setCustomerId(resultSet.getString(CommonConstants.COLUMN_INDEX_SEVEN));
				booking.setVehicleId(resultSet.getString(CommonConstants.COLUMN_INDEX_EIGHT));
				booking.setDriverId(resultSet.getString(CommonConstants.COLUMN_INDEX_NINE));
				booking.setExpense(resultSet.getDouble(CommonConstants.COLUMN_INDEX_TEN));
				booking.setAddedDate(resultSet.getDate(CommonConstants.COLUMN_INDEX_ELEVEN).toLocalDate());
				bookingList.add(booking);
			}

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			bookingLog.log(Level.SEVERE, e.getMessage());
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
				bookingLog.log(Level.SEVERE, e.getMessage());
			}
		}
		return bookingList;
	}

	/**
	 * Get the updated booking
	 * 
	 * @param bookingId
	 *            ID of the booking to remove or select from the list
	 * 
	 * @return return the Booking object
	 * 
	 */
	@Override
	public Booking updateBooking(String bookingId, Booking booking) {

		/*
		 * Before fetching booking it checks whether booking Id is available
		 */
		if (bookingId != null && !bookingId.isEmpty()) {
			/*
			 * Update booking query will be retrieved from BookingQuery.xml
			 */
			try {
				connection = JDBCUtility.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_UPDATE_BOOKING));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, booking.getBookingId());
				preparedStatement.setDate(CommonConstants.COLUMN_INDEX_TWO, Date.valueOf(booking.getPickupDate()));
				preparedStatement.setTime(CommonConstants.COLUMN_INDEX_THREE, Time.valueOf(booking.getPickupTime()));
				preparedStatement.setDate(CommonConstants.COLUMN_INDEX_FOUR, Date.valueOf(booking.getDropOffDate()));
				preparedStatement.setTime(CommonConstants.COLUMN_INDEX_FIVE, Time.valueOf(booking.getDropOffTime()));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_SIX, booking.getLocation());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_SEVEN, booking.getCustomerId());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_EIGHT, booking.getVehicleId());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_NINE, booking.getDriverId());
				preparedStatement.setDouble(CommonConstants.COLUMN_INDEX_TEN, booking.getExpense());
				preparedStatement.setDate(CommonConstants.COLUMN_INDEX_ELEVEN, Date.valueOf(booking.getAddedDate()));
				preparedStatement.executeUpdate();

			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				bookingLog.log(Level.SEVERE, e.getMessage());
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
					bookingLog.log(Level.SEVERE, e.getMessage());
				}
			}
		}
		// Get the updated booking
		return getBookingById(bookingId);
	}
	
	/**
	 *
	 * @return ArrayList<String> Array of booking id list will be return
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
	 *             - Indicates a serious configuration error.
	 * @throws NullPointerException
	 *             - Service is not available
	 */
	private ArrayList<String> getBookingIDs(){
		
		ArrayList<String> arrayList = new ArrayList<String>();
		/*
		 * List of booking Ids will be retrieved from BookingQuery.xml
		 */
		try {
			connection = JDBCUtility.getDBConnection();
			preparedStatement = connection
					.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_GET_BOOKING_IDS));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				arrayList.add(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
			}
		} catch (SQLException | SAXException | IOException | ParserConfigurationException
				| ClassNotFoundException e) {
			bookingLog.log(Level.SEVERE, e.getMessage());
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
				bookingLog.log(Level.SEVERE, e.getMessage());
			}
		}
		return arrayList;
	}
	
}
