package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.model.Customer;
import web.service.CustomerServiceImplementation;
import web.service.ICustomerService;


public class CustomerConfirmationServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerConfirmationServlet() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();

		String email = request.getParameter("email");
		String pwd = request.getParameter("password");
		
		ICustomerService iCustomerService1 = new CustomerServiceImplementation();
		ArrayList<Customer> customerList = iCustomerService1.getCustomers();
		
		for (Customer customer : customerList) {
			if (email.equals(customer.getEmail()) && pwd.equals(customer.getPassword())) {
				String customerId = customer.getCustomerID();
				
				request.setAttribute("customer", customerId);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/MyBookings.jsp");
				dispatcher.forward(request, response);
				break;
				
			} else {
				out.print("User Email or Password Error!");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/CustomerDetails.jsp");
				dispatcher.include(request, response);
			}			
		}	
	}
}
