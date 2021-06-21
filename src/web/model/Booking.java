
package web.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Booking {
	private String bookingId;
	private LocalDate pickupDate;
	private LocalTime pickupTime;
	private LocalDate dropOffDate;
	private LocalTime dropOffTime;
	private String location;
	private String customerId;
	private String vehicleId;
	private String driverId;
	private double expense;
	private LocalDate addedDate;
	
	
	/**
	 * @return the bookingId
	 */
	public String getBookingId() {
		return bookingId;
	}
	
	/**
	 * @param bookingId the bookingId to set
	 */
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	
	/**
	 * @return the pickupDate
	 */
	public LocalDate getPickupDate() {
		return pickupDate;
	}
	
	/**
	 * @param pickupDate the pickupDate to set
	 */
	public void setPickupDate(LocalDate pickupDate) {
		this.pickupDate = pickupDate;
	}
	
	/**
	 * @return the pickupTime
	 */
	public LocalTime getPickupTime() {
		return pickupTime;
	}
	
	/**
	 * @param pickupTime the pickupTime to set
	 */
	public void setPickupTime(LocalTime pickupTime) {
		this.pickupTime = pickupTime;
	}
	
	/**
	 * @return the dropOffDate
	 */
	public LocalDate getDropOffDate() {
		return dropOffDate;
	}
	
	/**
	 * @param dropOffDate the dropOffDate to set
	 */
	public void setDropOffDate(LocalDate dropOffDate) {
		this.dropOffDate = dropOffDate;
	}
	
	/**
	 * @return the dropOffTime
	 */
	public LocalTime getDropOffTime() {
		return dropOffTime;
	}
	
	/**
	 * @param dropOffTime the dropOffTime to set
	 */
	public void setDropOffTime(LocalTime dropOffTime) {
		this.dropOffTime = dropOffTime;
	}
	
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}
	
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	/**
	 * @return the vehicleId
	 */
	public String getVehicleId() {
		return vehicleId;
	}
	
	/**
	 * @param vehicleId the vehicleId to set
	 */
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	/**
	 * @return the driverId
	 */
	public String getDriverId() {
		return driverId;
	}
	
	/**
	 * @param driverId the driverId to set
	 */
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	
	/**
	 * @return the expense
	 */
	public double getExpense() {
		return expense;
	}

	/**
	 * @param expense the expense to set
	 */
	public void setExpense(double expense) {
		this.expense = expense;
	}

	/**
	 * @return the addedDate
	 */
	public LocalDate getAddedDate() {
		return addedDate;
	}

	/**
	 * @param addedDate the addedDate to set
	 */
	public void setAddedDate(LocalDate addedDate) {
		this.addedDate = addedDate;
	}
	
	
	
	/**
	 * 
	 * 
	 */
}
