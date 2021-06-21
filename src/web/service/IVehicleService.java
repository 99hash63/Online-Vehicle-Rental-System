
package web.service;

import java.util.ArrayList;
import java.util.logging.Logger;

import web.model.Vehicle;

public interface IVehicleService {
	
	public static final Logger vehicleLog = Logger.getLogger(IVehicleService.class.getName());
	
	public void addVehicle(Vehicle vehicle);

	public Vehicle getVehicleByID(String vehicleID);
	
	//public ArrayList<Vehicle> getVehiclesByType(String vType);
	
	public ArrayList<Vehicle> getVehicle();
	
	public Vehicle updateVehicle(String vehicleID, Vehicle vehicle);
	
	public void removeVehicle(String vehicleID);
	
}