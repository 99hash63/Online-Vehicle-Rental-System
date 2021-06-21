package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.model.Booking;
import web.model.Customer;
import web.service.ICustomerService;
import web.service.CustomerServiceImplementation;
//import web.service.IBookingService;
//import web.service.BookingServiceImplementation;

public class UserConfirmationServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserConfirmationServlet() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		Booking booking = (Booking) request.getAttribute("booking");
		
		RequestDispatcher dispatcher1 = getServletContext().getRequestDispatcher("/WEB-INF/views/ConfirmRegistration.jsp");
		dispatcher1.include(request, response);
		
		String email = request.getParameter("email");
		String password1 = request.getParameter("pwd1");
		String password2 = request.getParameter("pwd2");
		//String customerId;
		
		if (password1 != null) {
			ICustomerService iCustomerService1 = new CustomerServiceImplementation();
			ArrayList<Customer> customerList = iCustomerService1.getCustomers();
			
			for (Customer customer : customerList) {
				if (email.contentEquals(customer.getEmail())) {
					if (password1.equals(customer.getPassword())) {
						break;
					} else {
						out.print("Password Error!");
						RequestDispatcher dispatcher2 = getServletContext().getRequestDispatcher("/WEB-INF/views/ConfirmRegistration.jsp");
						dispatcher2.include(request, response);
					}
				} else {
					out.print("User Email Error!");
					RequestDispatcher dispatcher2 = getServletContext().getRequestDispatcher("/WEB-INF/views/ConfirmRegistration.jsp");
					dispatcher2.include(request, response);
				}
			}
		}
		
		if (password2 != null) {
			Customer newCustomer = new Customer();
			
			newCustomer.setCustomerFirstName(request.getParameter("fName"));
			newCustomer.setCustomerLastName(request.getParameter("lName"));
			newCustomer.setContactNumber(request.getParameter("contact"));
			newCustomer.setEmail(request.getParameter("email"));
			newCustomer.setPassword(request.getParameter("password2"));
			
			ICustomerService iCustomerService2 = new CustomerServiceImplementation();
			iCustomerService2.addCustomer(newCustomer);
			
		}
		
		ICustomerService iCustomerService3 = new CustomerServiceImplementation();
		ArrayList<Customer> list = iCustomerService3.getCustomers();
		
		for (Customer customer : list) {
			if (email.equals(customer.getEmail())) {
				String customerId = customer.getCustomerID();
				
				booking.setCustomerId(customerId);
			}
		}
		
		request.setAttribute("booking", booking);
		RequestDispatcher dispatcher3 = getServletContext().getRequestDispatcher("/ApplyChargesServlet");
		dispatcher3.forward(request, response);
		
	}
}
