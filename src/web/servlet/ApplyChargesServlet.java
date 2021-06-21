package web.servlet;

import java.io.IOException;
import java.time.Period;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.model.Booking;
import web.model.Vehicle;
import web.model.Driver;
import web.service.IBookingService;
import web.service.BookingServiceImplementation;
import web.service.IVehicleService;
import web.service.VehicleServiceImplementation;
import web.service.IDriverService;
import web.service.DriverServiceImplementation;

public class ApplyChargesServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApplyChargesServlet() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		
		Booking booking = (Booking) request.getAttribute("booking");
		
		String vehicleId = booking.getVehicleId();
		
		IVehicleService iVehicleService = new VehicleServiceImplementation();
		Vehicle vehicle = iVehicleService.getVehicleByID(vehicleId);
		
		Double ratePerDay = vehicle.getRatePerDay();
		
		Period days = Period.between(booking.getDropOffDate(), booking.getPickupDate());
		int noOfDays = days.getDays();
		
		Double totalExpense = ratePerDay * noOfDays;
		
		booking.setExpense(totalExpense);
		
		IBookingService iBookingService = new BookingServiceImplementation();
		iBookingService.addBooking(booking);
		
		IDriverService iDriverService = new DriverServiceImplementation();
		Driver driver = iDriverService.getDriversByID(booking.getDriverId());
		
		request.setAttribute("booking", booking);
		request.setAttribute("driver", driver);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ExpenseDetails.jsp");
		dispatcher.forward(request, response);
		
		
	}
}
