
package web.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.IBookingService;
import web.service.BookingServiceImplementation;

public class RemoveBookingServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveBookingServlet() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
	
		String bookingID = request.getParameter("bookingID");
		String customerID = request.getParameter("customerID");
	
		IBookingService iBookingService = new BookingServiceImplementation();
		iBookingService.removeBooking(bookingID);
		
		request.setAttribute("customer", customerID);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/MyBookings.jsp");
		dispatcher.forward(request, response);
		
	}
}
