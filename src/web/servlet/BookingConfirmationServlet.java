package web.servlet;

import java.io.IOException;
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


public class BookingConfirmationServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookingConfirmationServlet() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		Booking booking = (Booking) request.getAttribute("booking");
		
		IVehicleService iVehicleService = new VehicleServiceImplementation();
		Vehicle vehicle = iVehicleService.getVehicleByID(booking.getVehicleId());
		
		IDriverService iDriverService = new DriverServiceImplementation();
		Driver driver = iDriverService.getDriversByID(booking.getDriverId());
		
		request.setAttribute("booking", booking);
		request.setAttribute("vehicle", vehicle);
		request.setAttribute("driver", driver);
		RequestDispatcher dispatcher1 = getServletContext().getRequestDispatcher("/WEB-INF/views/BookingDetails.jsp");
		dispatcher1.include(request, response);
		
		request.setAttribute("booking", booking);
		RequestDispatcher dispatcher2 = getServletContext().getRequestDispatcher("/UserConfirmationServlet");
		dispatcher2.forward(request, response);
	
	}	



}