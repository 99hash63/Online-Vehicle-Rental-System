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

public class GetVehicleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetVehicleServlet() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

 		String vehicleID = request.getParameter("vehicleID");			
		IVehicleService iVehicleService = new VehicleServiceImplementation();
		Vehicle vehicle = iVehicleService.getVehicleByID(vehicleID);

		request.setAttribute("vehicle", vehicle);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/GetVehicle.jsp");
		dispatcher.forward(request, response);
	
	}

}
