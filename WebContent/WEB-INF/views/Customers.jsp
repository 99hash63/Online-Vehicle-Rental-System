<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="web.model.Customer"%>
<%@page import="web.service.ICustomerService"%>
<%@page import="web.service.CustomerServiceImplementation"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>
		<table>
			<tr>
				<th>Customer ID</th>
				<th>Customer First Name</th>
				<th>Customer Last Name</th>
				<th>Contact Number</th>
				<th>User Email</th>
			</tr>
			<%
            	ICustomerService iCustomerService = new CustomerServiceImplementation();
				ArrayList<Customer> customerList = iCustomerService.getCustomers();
            
            	for (Customer customer : customerList) {
            		
            %>
            <tr>
            	<td><%= customer.getCustomerID() %></td>
            	<td><%= customer.getCustomerFirstName() %></td>
            	<td><%= customer.getCustomerLastName() %></td>
            	<td><%= customer.getContactNumber() %></td>
            	<td><%= customer.getEmail() %></td>
            </tr>
            <%
            	}
            %>
		</table>
	</div>
	<div>
		<a href="/WEB-INF/views/AdminDashboard.jsp"><input type="button" value="Back To Dashboard" ></a>
	</div>           
</body>
</html>