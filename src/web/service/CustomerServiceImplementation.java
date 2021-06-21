
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

import web.model.Customer;
import web.utility.CommonConstants;
import web.utility.CommonUtility;
import web.utility.JDBCUtility;
import web.utility.QueryUtility;



public class CustomerServiceImplementation implements ICustomerService {
	
	public static final Logger customerLog = Logger.getLogger(CustomerServiceImplementation.class.getName());

	private static Connection connection;

	private static Statement statement;
	
	static{
		
		createCustomerTable();
	}
	
	private PreparedStatement preparedStatement;
	
	public static void createCustomerTable() {

		try {
			connection = JDBCUtility.getDBConnection();
			statement = connection.createStatement();
			statement.executeUpdate(QueryUtility.queryById(CommonConstants.QUERY_ID_DROP_TABLE_CUSTOMER));
			statement.executeUpdate(QueryUtility.queryById(CommonConstants.QUERY_ID_CREATE_TABLE_CUSTOMER));

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			customerLog.log(Level.SEVERE, e.getMessage());
		}
	}
	
	@Override
	public void addCustomer(Customer customer) {

		String customerID = CommonUtility.generateCustomerIds(getCustomerIDs());
		
		try {
			connection = JDBCUtility.getDBConnection();
			
			preparedStatement = connection
					.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_INSERT_CUSTOMERS));
			connection.setAutoCommit(false);
			
			
			customer.setCustomerID(customerID);
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, customer.getCustomerID());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, customer.getCustomerFirstName());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, customer.getCustomerLastName());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, customer.getContactNumber());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, customer.getEmail());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_SIX, customer.getPassword());
			
			preparedStatement.execute();
			connection.commit();

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			customerLog.log(Level.SEVERE, e.getMessage());
		} finally {
			
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				customerLog.log(Level.SEVERE, e.getMessage());
			}
		}
	}
	
	@Override
	public Customer getCustomerByID(String customerID) {

		return actionOnCustomer(customerID).get(0);
	}
	
	@Override
	public ArrayList<Customer> getCustomers() {
		
		return actionOnCustomer(null);
	}
	
	@Override
	public void removeCustomer(String customerID) {

		if (customerID != null && !customerID.isEmpty()) {
			
			try {
				connection = JDBCUtility.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_REMOVE_CUSTOMER));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, customerID);
				preparedStatement.executeUpdate();
			} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
				customerLog.log(Level.SEVERE, e.getMessage());
			} finally {
				
				try {
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					customerLog.log(Level.SEVERE, e.getMessage());
				}
			}
		}
	}
	
	private ArrayList<Customer> actionOnCustomer(String customerID) {

		ArrayList<Customer> customerList = new ArrayList<Customer>();
		try {
			connection = JDBCUtility.getDBConnection();
			
			if (customerID != null && !customerID.isEmpty()) {
				
				preparedStatement = connection
						.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_GET_CUSTOMER));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, customerID);
			}
			
			else {
				preparedStatement = connection
						.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_ALL_CUSTOMERS));
			}
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				customer.setCustomerFirstName(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				customer.setCustomerLastName(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				customer.setContactNumber(resultSet.getString(CommonConstants.COLUMN_INDEX_FOUR));
				customer.setEmail(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
				customer.setPassword(resultSet.getString(CommonConstants.COLUMN_INDEX_SIX));
				customerList.add(customer);
			}

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			customerLog.log(Level.SEVERE, e.getMessage());
		} finally {
			
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				customerLog.log(Level.SEVERE, e.getMessage());
			}
		}
		return customerList;
	}
	
	private ArrayList<String> getCustomerIDs(){
		
		ArrayList<String> arrayList = new ArrayList<String>();
		
		try {
			connection = JDBCUtility.getDBConnection();
			preparedStatement = connection
					.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_GET_CUSTOMER_IDS));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				arrayList.add(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
			}
		} catch (SQLException | SAXException | IOException | ParserConfigurationException
				| ClassNotFoundException e) {
			customerLog.log(Level.SEVERE, e.getMessage());
		} finally {
			
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				customerLog.log(Level.SEVERE, e.getMessage());
			}
		}
		return arrayList;
	}



}

