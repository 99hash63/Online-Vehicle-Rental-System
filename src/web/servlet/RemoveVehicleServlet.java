package web.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.IVehicleService;
import web.service.VehicleServiceImplementation;

public class RemoveVehicleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveVehicleServlet() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
	
		String vehicleID = request.getParameter("vID");
	
		IVehicleService iVehicleService = new VehicleServiceImplementation();
		iVehicleService.removeVehicle(vehicleID);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/Vehicles.jsp");
		dispatcher.forward(request, response);
		
	}
}
