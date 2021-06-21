package web.model;

public class Admin {

	private String AdminID;
	
	private String firstName;
	
	private String lastName;

	private String ContactNumber;

	private String Email;

	private String Password;

	
	/**
	 * @return the adminID
	 */
	public String getAdminID() {
		return AdminID;
	}

	/**
	 * @param adminID the adminID to set
	 */
	public void setAdminID(String adminID) {
		AdminID = adminID;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return ContactNumber;
	}

	/**
	 * @param contactNumber the contactNumber to set
	 */
	public void setContactNumber(String contactNumber) {
		ContactNumber = contactNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return Email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		Email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return Password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		Password = password;
	}
	
	
	
	
}
