package web.service;

import web.model.Customer;
import java.util.ArrayList;
import java.util.logging.Logger;

public interface ICustomerService {

	public static final Logger customerLog = Logger.getLogger(ICustomerService.class.getName());
	
	public void addCustomer(Customer customer);
	
	public Customer getCustomerByID(String customerID);
	
	public ArrayList<Customer> getCustomers();
	
	public void removeCustomer(String customerID);
}

