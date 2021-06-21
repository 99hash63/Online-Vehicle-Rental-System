
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

import web.model.Admin;
import web.utility.CommonUtility;
import web.utility.JDBCUtility;
import web.utility.QueryUtility;
import web.utility.CommonConstants;

public class AdminServiceImplementation implements IAdminService {
		

		public static final Logger adminLog = Logger.getLogger(AdminServiceImplementation.class.getName());

		//private static final String Email = null;

		private static Connection connection;

		private static Statement statement;

		static{
			
			createAdminTable();
		}

		private PreparedStatement preparedStatement;

		
		public static void createAdminTable() {

			try {
				connection = JDBCUtility.getDBConnection();
				statement = connection.createStatement();
				statement.executeUpdate(QueryUtility.queryById(CommonConstants.QUERY_ID_DROP_TABLE_ADMIN));
				statement.executeUpdate(QueryUtility.queryById(CommonConstants.QUERY_ID_CREATE_TABLE_ADMIN));

			} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
				adminLog.log(Level.SEVERE, e.getMessage());
			}
		}

		
		@Override
		public void addAdmin(Admin admin) {

			String adminID = CommonUtility.generateAdminIds(getAdminIDs());
			
			try {
				connection = JDBCUtility.getDBConnection();
				
				preparedStatement = connection
						.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_INSERT_ADMINS));
				connection.setAutoCommit(false);
				
				
				admin.setAdminID(adminID);
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, admin.getAdminID());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, admin.getFirstName());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, admin.getLastName());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, admin.getContactNumber());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, admin.getEmail());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_SIX, admin.getPassword());
				
				preparedStatement.execute();
				connection.commit();

			} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
				adminLog.log(Level.SEVERE, e.getMessage());
			} finally {
				
				try {
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					adminLog.log(Level.SEVERE, e.getMessage());
				}
			}
		}

		@Override
		public Admin getAdminById(String adminID) {

			return actionOnAdmin(adminID).get(0);
		}
		
		
		@Override
		public ArrayList<Admin> getAdmins() {
			
			return actionOnAdmin(null);
		}

		
		@Override
		public void removeAdmin(String adminID) {

			
			if (adminID != null && !adminID.isEmpty()) {
				
				try {
					connection = JDBCUtility.getDBConnection();
					preparedStatement = connection
							.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_REMOVE_ADMIN));
					preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, adminID);
					preparedStatement.executeUpdate();
				} catch (SQLException | SAXException | IOException | ParserConfigurationException
						| ClassNotFoundException e) {
					adminLog.log(Level.SEVERE, e.getMessage());
				} finally {
					
					try {
						if (preparedStatement != null) {
							preparedStatement.close();
						}
						if (connection != null) {
							connection.close();
						}
					} catch (SQLException e) {
						adminLog.log(Level.SEVERE, e.getMessage());
					}
				}
			}
		}

	
		private ArrayList<Admin> actionOnAdmin(String adminID) {

			ArrayList<Admin> adminList = new ArrayList<Admin>();
			try {
				connection = JDBCUtility.getDBConnection();
				
				if (adminID != null && !adminID.isEmpty()) {
					
					preparedStatement = connection
							.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_GET_ADMIN));
					preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, adminID);
				}
				
				else {
					preparedStatement = connection
							.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_ALL_ADMINS));
				}
				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					Admin admin = new Admin();
					admin.setAdminID(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
					admin.setFirstName(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
					admin.setLastName(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
					admin.setContactNumber(resultSet.getString(CommonConstants.COLUMN_INDEX_FOUR));
					admin.setEmail(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
					admin.setPassword(resultSet.getString(CommonConstants.COLUMN_INDEX_SIX));
					adminList.add(admin);
				}

			} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
				adminLog.log(Level.SEVERE, e.getMessage());
			} finally {
			
				try {
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					adminLog.log(Level.SEVERE, e.getMessage());
				}
			}
			return adminList;
		}

		
		@Override
		public Admin updateAdmin(String adminID, Admin admin) {

			
			if (adminID != null && !adminID.isEmpty()) {
				
				try {
					connection = JDBCUtility.getDBConnection();
					preparedStatement = connection
							.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_UPDATE_ADMIN));
					preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, admin.getAdminID());
					preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, admin.getFirstName());
					preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, admin.getLastName());
					preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, admin.getContactNumber());
					preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, admin.getEmail());
					preparedStatement.setString(CommonConstants.COLUMN_INDEX_SIX, admin.getPassword());
					preparedStatement.executeUpdate();

				} catch (SQLException | SAXException | IOException | ParserConfigurationException
						| ClassNotFoundException e) {
					adminLog.log(Level.SEVERE, e.getMessage());
				} finally {
					
					try {
						if (preparedStatement != null) {
							preparedStatement.close();
						}
						if (connection != null) {
							connection.close();
						}
					} catch (SQLException e) {
						adminLog.log(Level.SEVERE, e.getMessage());
					}
				}
			}
			
			return getAdminById(adminID);
		}
		
		
		private ArrayList<String> getAdminIDs(){
			
			ArrayList<String> arrayList = new ArrayList<String>();
			
			try {
				connection = JDBCUtility.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtility.queryById(CommonConstants.QUERY_ID_GET_ADMIN_IDS));
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					arrayList.add(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				}
			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				adminLog.log(Level.SEVERE, e.getMessage());
			} finally {
				
				try {
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					adminLog.log(Level.SEVERE, e.getMessage());
				}
			}
			return arrayList;
		}

}

