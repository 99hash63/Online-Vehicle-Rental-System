
package web.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.model.Booking;
import web.model.Vehicle;
import web.service.BookingServiceImplementation;
import web.service.IBookingService;
import web.service.VehicleServiceImplementation;
import web.service.IVehicleService;

public class UpdateBookingServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateBookingServlet() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

 		Booking newBooking = new Booking();
 		String bookingID = request.getParameter("bookingID");
 		newBooking.setBookingId(bookingID);
 		newBooking.setPickupDate(LocalDate.parse(request.getParameter("pickupDate")));
 		newBooking.setPickupTime(LocalTime.parse(request.getParameter("pickupTime")));
 		newBooking.setDropOffDate(LocalDate.parse(request.getParameter("dropOffDate")));
 		newBooking.setDropOffTime(LocalTime.parse(request.getParameter("dropOffTime")));
 		newBooking.setLocation(request.getParameter("location"));
 		String customerID = request.getParameter("customerID");
 		newBooking.setCustomerId(customerID);
 		newBooking.setVehicleId(request.getParameter("vehicleID"));
 		newBooking.setDriverId(request.getParameter("driverID"));
 		newBooking.setAddedDate(LocalDate.parse(request.getParameter("addedDate")));
 		
 		Period days = Period.between(newBooking.getDropOffDate(), newBooking.getPickupDate());
 		int totalDays = days.getDays();
 		
 		IVehicleService iVehicleService = new VehicleServiceImplementation();
 		Vehicle vehicle = iVehicleService.getVehicleByID(newBooking.getVehicleId());
 		
 		Double rate = vehicle.getRatePerDay();
 		newBooking.setExpense(rate * totalDays);
 		
		IBookingService iBookingService = new BookingServiceImplementation();
		iBookingService.updateBooking(bookingID, newBooking);
		
		request.setAttribute("customer", customerID);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/MyBookings.jsp");
		dispatcher.forward(request, response);
	}
	
}

