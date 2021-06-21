
package web.service;

import web.model.Booking;

import java.util.ArrayList;
//import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.xml.transform.TransformerConfigurationException;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactoryConfigurationError;

public interface IBookingService {

	/** Initialize logger */
	public static final Logger bookingLog = Logger.getLogger(IBookingService.class.getName());
	
	/**
	 * Add Bookings for Booking table
	 * @param booking
	 */
	public void addBooking(Booking booking);

	/**
	 * Get a particular Booking
	 * 
	 * @param bookingId
	 * @return Booking
	 */
	public Booking getBookingById(String bookingId);
	
	/**
	 * Get a particular Booking
	 * 
	 * @param customerId
	 * @return Booking
	 */
	public ArrayList<Booking> getBookingByCustomerId(String customerId);
	
	/**
	 * Get all list of Bookings
	 * 
	 * @return ArrayList<Booking>
	 */
	public ArrayList<Booking> getBookings();
	
	/**
	 * Update existing Booking
	 * @param bookingId
	 * @param booking
	 * 
	 * @return
	 */
	public Booking updateBooking(String bookingId, Booking booking);

	/**
	 * Remove existing Booking
	 * 
	 * @param bookingId
	 */
	public void removeBooking(String bookingId);
	
}
