
package web.model;

public class Vehicle {

	private String vehicleID;
	
	private String name;

	private String vType;
	
	private String tType;
	
	private boolean airConditioning;

	private boolean availability;

	private String imageUrl;
	
	private double ratePerDay;
	
	
	/**
	 * @return the vehicleID
	 */
	public String getVehicleID() {
		return vehicleID;
	}

	/**
	 * @param vehicleID the vehicleID to set
	 */
	public void setVehicleID(String vehicleID) {
		this.vehicleID = vehicleID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the vType
	 */
	public String getvType() {
		return vType;
	}

	/**
	 * @param vType the vType to set
	 */
	public void setvType(String vType) {
		this.vType = vType;
	}
	
	/**
	 * @return the tType
	 */
	public String gettType() {
		return tType;
	}

	/**
	 * @param tType the tType to set
	 */
	public void settType(String tType) {
		this.tType = tType;
	}
	
	/**
	 * @return the airConditioning
	 */
	public boolean isAirConditioning() {
		return airConditioning;
	}

	/**
	 * @param airConditioning the airConditioning to set
	 */
	public void setAirConditioning(boolean airConditioning) {
		this.airConditioning = airConditioning;
	}

	/**
	 * @return the availability
	 */
	public boolean isAvailability() {
		return availability;
	}

	/**
	 * @param availability the availability to set
	 */
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * @return the ratePerDay
	 */
	public double getRatePerDay() {
		return ratePerDay;
	}

	/**
	 * @param ratePerDay the ratePerDay to set
	 */
	public void setRatePerDay(double ratePerDay) {
		this.ratePerDay = ratePerDay;
	}

	
	
}

