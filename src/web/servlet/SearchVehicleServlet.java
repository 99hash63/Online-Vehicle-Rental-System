package web.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.model.Booking;
import web.model.Vehicle;
import web.model.Driver;
import web.service.IVehicleService;
import web.service.VehicleServiceImplementation;
import web.service.IDriverService;
import web.service.DriverServiceImplementation;


public class SearchVehicleServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchVehicleServlet() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		
		Booking booking = new Booking();
		
		booking.setPickupDate(LocalDate.parse(request.getParameter("pickupDate")));
		booking.setPickupTime(LocalTime.parse(request.getParameter("pickupTime")));
		booking.setDropOffDate(LocalDate.parse(request.getParameter("dropOffDate")));
		booking.setDropOffTime(LocalTime.parse(request.getParameter("dropOffTime")));
		booking.setLocation(request.getParameter("location"));
		booking.setAddedDate(LocalDate.now());
		
		String vehicleType = request.getParameter("type");
		
		IVehicleService iVehicleService = new VehicleServiceImplementation();
		ArrayList<Vehicle> vehicleList = iVehicleService.getVehicle();
		
		ArrayList<Vehicle> array = new ArrayList<Vehicle> ();
		
		for (Vehicle vehicle : vehicleList) {
			if (vehicle.getvType() == vehicleType && vehicle.isAvailability() == true) {
				array.add(vehicle);
			} else {
				continue;
			}
		}
		
		request.setAttribute("array", array);
		RequestDispatcher dispatcher1 = getServletContext().getRequestDispatcher("/WEB-INF/views/VehicleSuggestions.jsp");
		dispatcher1.include(request, response);
		
		String vehicleID = request.getParameter("vehicleID");
		
		booking.setVehicleId(vehicleID);
		
		Vehicle vehicle = iVehicleService.getVehicleByID(vehicleID);
		
		vehicle.setAvailability(false);
		iVehicleService.updateVehicle(vehicleID, vehicle);
		
		IDriverService iDriverService = new DriverServiceImplementation();
		ArrayList<Driver> driverList = iDriverService.getDrivers();
		
		for (Driver driver : driverList) {
			if (driver.isAvailability() == true) {
				booking.setDriverId(driver.getDriverID());
				driver.setAvailability(false);
				break;
				
			} else {
				continue;
			}
		}
		
		
		request.setAttribute("booking", booking);
		RequestDispatcher dispatcher2 = getServletContext().getRequestDispatcher("/BookingConfirmationServlet");
		dispatcher2.forward(request, response);
		
	}
	
}
