
package web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.model.Booking;
import web.service.BookingServiceImplementation;
import web.service.IBookingService;

public class ManageBookingServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageBookingServlet() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

 		String bookingID = request.getParameter("bookingID");
		
		IBookingService iBookingService = new BookingServiceImplementation();
		Booking booking = iBookingService.getBookingById(bookingID);

		request.setAttribute("booking", booking);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ManageBooking.jsp");
		dispatcher.forward(request, response);
	}
	
}
