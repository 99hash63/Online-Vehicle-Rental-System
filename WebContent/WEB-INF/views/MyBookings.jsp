<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="web.model.Booking"%>
<%@page import="web.service.IBookingService"%>
<%@page import="web.service.BookingServiceImplementation"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.Period"%>

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
				<th>Booking ID</th>
                <th>Pickup Date</th>
                <th>Pickup Time</th>
                <th>DropOff Date</th>
                <th>DropOff Time</th>
                <th>Location</th>
                <th>Booking Added Date</th>
                <th>Service Charge</th>
                <th>Manage</th>
                <th>Remove</th>
            </tr>
            
            <%	
            	String customerId = (String) request.getAttribute("customer");
            
	            IBookingService iBookingService = new BookingServiceImplementation();
				ArrayList<Booking> bookingList = iBookingService.getBookingByCustomerId(customerId);
            
            	for (Booking booking : bookingList) {
            		
            %>
            <tr>
            	<td><%= booking.getBookingId() %></td>
            	<td><%= booking.getPickupDate() %></td>
            	<td><%= booking.getPickupTime() %></td>
            	<td><%= booking.getDropOffDate() %></td>
            	<td><%= booking.getDropOffTime() %></td>
            	<td><%= booking.getLocation() %></td>
            	<td><%= booking.getAddedDate() %></td>
            	<td><%= System.out.format("%.2f", booking.getExpense()) %></td>
            	
				<%	
					Period noOfDays = Period.between(booking.getPickupDate(), LocalDate.now());
					int days = noOfDays.getDays();
					
					if (days > 1) {
						
				%>
				<td>
					<form method="POST" action="ManageBookingServlet">
						<input type="hidden" name="bookingID" value="<%= booking.getBookingId() %>" >
				 		<input type="submit" value= "Manage Booking" > 
					</form>
				</td>
				<td>
					<form method="POST" action="RemoveBookingServlet">
						<input type="hidden" name="bookingID" value="<%= booking.getBookingId() %>" >
						<input type="hidden" name="customerID" value="<%= customerId %>" >
					 	<input type="submit" value= "Remove Booking" > 
				 	</form>
				</td>
				<%
					} else {
						
				%> 
				<td>Unavailable</td>
				<td>Unavailable</td>
				<%
					}
				%>
			
			</tr>          
            <%
            	}
            %>
            
		</table>
	</div>
</body>
</html>