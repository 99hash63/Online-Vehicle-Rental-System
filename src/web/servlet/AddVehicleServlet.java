
package web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.model.Vehicle;
import web.service.IVehicleService;
import web.service.VehicleServiceImplementation;

public class AddVehicleServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddVehicleServlet() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		Vehicle newVehicle = new Vehicle();
		
		newVehicle.setName(request.getParameter("vName"));
		newVehicle.setvType(request.getParameter("vType"));
		newVehicle.settType(request.getParameter("tType"));
		newVehicle.setAirConditioning(Boolean.parseBoolean(request. getParameter("AC")));
		newVehicle.setImageUrl(request.getParameter("vImage"));
		newVehicle.setRatePerDay(Double.parseDouble(request.getParameter("vRate")));
		
		IVehicleService iVehicleservice = new VehicleServiceImplementation();
		iVehicleservice.addVehicle(newVehicle);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/Vehicles.jsp");
		dispatcher.forward(request, response);
	}

}
