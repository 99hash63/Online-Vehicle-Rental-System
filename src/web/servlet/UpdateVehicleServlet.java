package web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.model.Vehicle;
import web.service.VehicleServiceImplementation;
import web.service.IVehicleService;

public class UpdateVehicleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateVehicleServlet() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		
		Vehicle vehicle = new Vehicle();
		
		String vehicleID = request.getParameter("vID");
		vehicle.setVehicleID(vehicleID);
		vehicle.setName(request.getParameter("vName"));
		vehicle.setvType(request.getParameter("vType"));
		vehicle.settType(request.getParameter("tType"));
		vehicle.setAirConditioning(Boolean.parseBoolean(request.getParameter("AC")));
		vehicle.setAvailability(Boolean.parseBoolean(request.getParameter("available")));
		vehicle.setImageUrl(request.getParameter("vImage"));
		vehicle.setRatePerDay(Double.parseDouble(request.getParameter("vRate")));
		
		IVehicleService iVehicleService = new VehicleServiceImplementation();
		iVehicleService.updateVehicle(vehicleID, vehicle);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/Vehicles.jsp");
		dispatcher.forward(request, response);
		
	}
	
}
