<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="web.model.Booking"%>
<%@page import="web.service.IBookingService"%>
<%@page import="web.service.BookingServiceImplementation"%>
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
				<th>Booking ID</th>
                <th>Pickup Date</th>
                <th>Pickup Time</th>
                <th>DropOff Date</th>
                <th>DropOff Time</th>
                <th>Location</th>
                <th>Customer ID</th>
                <th>Vehicle ID</th>
                <th>Driver ID</th>
                <th>Service Charge</th>
                <th>Booking Added Date</th>
            </tr>
            <%
            	IBookingService iBookingService = new BookingServiceImplementation();
				ArrayList<Booking> bookingList = iBookingService.getBookings();
            
            	for (Booking booking : bookingList) {
            		
            %>
            <tr>
            	<td><%= booking.getBookingId() %></td>
            	<td><%= booking.getPickupDate() %></td>
            	<td><%= booking.getPickupTime() %></td>
            	<td><%= booking.getDropOffDate() %></td>
            	<td><%= booking.getDropOffTime() %></td>
            	<td><%= booking.getLocation() %></td>
            	<td><%= booking.getCustomerId() %></td>
            	<td><%= booking.getVehicleId() %></td>
            	<td><%= booking.getDriverId() %></td>
            	<td><%= System.out.format("%.2f", booking.getExpense()) %></td>
            	<td><%= booking.getAddedDate() %></td>
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