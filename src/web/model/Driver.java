
package web.model;

public class Driver {

	private String DriverID;
	
	private String FirstName;
	
	private String LastName;

	private int Age;

	private String NIC;
	
	private String License;
	
	private String Sex;
	
	private boolean availability;
	
	//private int Rating;

	
	/**
	 * @return the driverID
	 */
	public String getDriverID() {
		return DriverID;
	}

	/**
	 * @param driverID the driverID to set
	 */
	public void setDriverID(String driverID) {
		DriverID = driverID;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return FirstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return LastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		LastName = lastName;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return Age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		Age = age;
	}

	/**
	 * @return the nIC
	 */
	public String getNIC() {
		return NIC;
	}

	/**
	 * @param nIC the nIC to set
	 */
	public void setNIC(String nIC) {
		NIC = nIC;
	}

	/**
	 * @return the license
	 */
	public String getLicense() {
		return License;
	}

	/**
	 * @param lisence the license to set
	 */
	public void setLicense(String license) {
		License = license;
	}
	
	/**
	 * @return the sex
	 */
	public String getSex() {
		return Sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		Sex = sex;
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
	 * @return the rating
	 
	public int getRating() {
		return Rating;
	} */

	/**
	 * @param rating the rating to set
	 
	public void setRating(int rating) {
		Rating = rating;
	}*/
		
}
