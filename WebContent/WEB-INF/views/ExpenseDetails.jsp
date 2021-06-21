<%@page import="web.model.Driver"%>
<%@page import="web.model.Booking"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		Booking booking = (Booking) request.getAttribute("booking");
		Driver driver = (Driver) request.getAttribute("driver");
	%>
	<div>
		<pre>Your Booking is completed!. From our site you can manage your Booking, you can either remove
			 or change your Booking details. In order to do so, just go to the "Manage Booking" link 
		 	 provided in the Home page. But if you wants to manage your Booking, you must do so before 
		 	 24 Hrs, before the Pickup Date. We expect that our Service will be up to your expectations. 
		 	 A Summary of the Booking is given below. Feel free to visit us again. Thank You.
		</pre>
	</div>
	<div>
		<table>
			<caption>Booking Summary</caption>
			<tr>
				<th>Booking ID:</th>
				<td><%=booking.getBookingId() %></td>
			</tr>
			<tr>
				<th>Driver Name:</th>
				<td><%=driver.getFirstName() + " " + driver.getLastName() %></td>
			</tr>
			<tr>
				<th>Pickup Date:</th>
				<td><%=booking.getPickupDate() %></td>
			</tr>
			<tr>
				<th>Pickup Time:</th>
				<td><%=booking.getPickupTime() %></td>
			</tr>
			<tr>
				<th>Pickup Location:</th>
				<td><%=booking.getLocation() %></td>
			</tr>
			<tr>
				<th>Total Charge for the Service:</th>
				<td><%=System.out.format("%.2f", booking.getExpense()) %></td>
			</tr>
		</table>
	</div>	
	<div>
		<a href="index.jsp" ><input type="button" Value="BACK TO HOME" ></a>
	</div>
</body>
</html>