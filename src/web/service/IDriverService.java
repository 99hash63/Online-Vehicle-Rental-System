
package web.service;

import web.model.Driver;
import java.util.ArrayList;
import java.util.logging.Logger;

public interface IDriverService {
	
	public static final Logger driverLog = Logger.getLogger(IDriverService.class.getName());
	
	public void addDriver(Driver driver);

	public Driver getDriversByID(String DriverID);
	
	public ArrayList<Driver> getDrivers();
	
	public Driver updateDriver(String DriverID, Driver driver);

	public void removeDriver(String DriverID);
	
	
}

