<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="web.model.Booking"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		Booking booking = (Booking) request.getAttribute("booking");
	%>
	<div>
	<form method="POST" action="UpdateBookingServlet">
		<table>
			<tr>
				<td>Booking ID</td>
				<td>
					<input type="text" name="bookingID" value="<%=booking.getBookingId() %>" disabled="disabled" >
				</td>
			</tr>
			<tr>
				<td>Pickup Date</td>
				<td>
					<input type="text" name="pickupDate" value="<%=booking.getPickupDate() %>" >
				</td>
			</tr>
			<tr>
				<td>Pickup Time</td>
				<td>
					<input type="text" name="pickupTime" value="<%=booking.getPickupTime() %>" >
				</td>
			</tr>
			<tr>
				<td>DropOff Date</td>
				<td>
					<input type="text" name="dropOffDate" value="<%=booking.getDropOffDate() %>" >
				</td>
			</tr>
			<tr>
				<td>DropOff Time</td>
				<td>
					<input type="text" name="dropOffTime" value="<%=booking.getDropOffTime() %>" >
				</td>
			</tr>
			<tr>
				<td>Location</td>
				<td>
					<input type="text" name="location" value="<%=booking.getLocation() %>" >
				</td>
			</tr>
			<tr>
				<td>Booking Added Date</td>
				<td>
					<input type="text" name="addedDate" value="<%=booking.getAddedDate() %>" disabled="disabled">
				</td>
			</tr>
			<tr>
				<td>Service Charge</td>
				<td>
					<input type="text" name="charges" value="<%=System.out.format("%.2f", booking.getExpense()) %> " disabled="disabled" >
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="hidden" name="bookingID" value="<%=booking.getBookingId() %>" > 
					<input type="hidden" name="customerID" value="<%=booking.getCustomerId() %>" >
					<input type="hidden" name="vehicleID" value="<%=booking.getAddedDate() %>" >
					<input type="hidden" name="driverID" value="<%=booking.getDriverId() %>" >
					<input type="submit" value="Update Booking" >
				</td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>