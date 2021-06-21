package web.utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import web.service.IAdminService;
import web.service.IBookingService;
import web.service.ICustomerService;
import web.service.IDriverService;
import web.service.IVehicleService;

/**
 * This is the common utility class to load all property details at once when it
 * is initializing .
 */
public class CommonUtility {

	/** Initialize logger */
	public static final Logger bookingLog = Logger.getLogger(IBookingService.class.getName());
	public static final Logger vehicleLog = Logger.getLogger(IVehicleService.class.getName());
	public static final Logger driverLog = Logger.getLogger(IDriverService.class.getName());
	public static final Logger customerLog = Logger.getLogger(ICustomerService.class.getName());
	public static final Logger adminLog = Logger.getLogger(IAdminService.class.getName());

	public static final Properties properties = new Properties();

	static {
		try {
			
			// Read the property only once when load the class
			properties.load(QueryUtility.class.getResourceAsStream(CommonConstants.PROPERTY_FILE));
			
		} catch (IOException e) {
			bookingLog.log(Level.SEVERE, e.getMessage());
			vehicleLog.log(Level.SEVERE, e.getMessage());
			driverLog.log(Level.SEVERE, e.getMessage());
			customerLog.log(Level.SEVERE, e.getMessage());
			adminLog.log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 * Add new Booking Id
	 * 
	 * @param arrayList
	 * @return
	 */
	public static String generateBookingIds(ArrayList<String> arrayList) {
			String id;
			int next = arrayList.size();
			next++;
			id = CommonConstants.BOOKING_ID_PREFIX + next;
			if (arrayList.contains(id)) {
				next++;
				id = CommonConstants.BOOKING_ID_PREFIX + next;
			}
			return id;
			
	}
	
	/**
	 * Add new Booking Id
	 * 
	 * @param arrayList
	 * @return
	 */
	public static String generateVehicleIds(ArrayList<String> arrayList) {
			String id;
			int next = arrayList.size();
			next++;
			id = CommonConstants.VEHICLE_ID_PREFIX + next;
			if (arrayList.contains(id)) {
				next++;
				id = CommonConstants.VEHICLE_ID_PREFIX + next;
			}
			return id;
			
	}
	
	/**
	 * Add new Booking Id
	 * 
	 * @param arrayList
	 * @return
	 */
	public static String generateDriverIds(ArrayList<String> arrayList) {
			String id;
			int next = arrayList.size();
			next++;
			id = CommonConstants.DRIVER_ID_PREFIX + next;
			if (arrayList.contains(id)) {
				next++;
				id = CommonConstants.DRIVER_ID_PREFIX + next;
			}
			return id;
			
	}
	
	/**
	 * Add new Booking Id
	 * 
	 * @param arrayList
	 * @return
	 */
	public static String generateCustomerIds(ArrayList<String> arrayList) {
			String id;
			int next = arrayList.size();
			next++;
			id = CommonConstants.CUSTOMER_ID_PREFIX + next;
			if (arrayList.contains(id)) {
				next++;
				id = CommonConstants.CUSTOMER_ID_PREFIX + next;
			}
			return id;
			
	}
	
	/**
	 * Add new Booking Id
	 * 
	 * @param arrayList
	 * @return
	 */
	public static String generateAdminIds(ArrayList<String> arrayList) {
			String id;
			int next = arrayList.size();
			next++;
			id = CommonConstants.ADMIN_ID_PREFIX + next;
			if (arrayList.contains(id)) {
				next++;
				id = CommonConstants.ADMIN_ID_PREFIX + next;
			}
			return id;
			
	}
	
}
