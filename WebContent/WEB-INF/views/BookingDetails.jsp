<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="web.model.Booking"%>
<%@page import="web.model.Vehicle"%>
<%@page import="web.model.Driver"%> 
<%-- <%@page import="web.service.IVehicleService"%>    --%>
<%-- <%@page import="web.service.VehicleServiceImplementation"%>   --%>
<%-- <%@page import="web.service.IDriverService"%>  --%>
<%-- <%@page import="web.service.DriverServiceImplementation"%>       --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<%
		Booking booking = (Booking) request.getAttribute("booking");
		Vehicle vehicle = (Vehicle) request.getAttribute("vehicle");
		Driver driver = (Driver) request.getAttribute("driver");
		
	%>
	
	<div>
		<table>
			<caption>Booking Details</caption>
			<tr>
				<th>Pickup Date:</th>
				<td><%=booking.getPickupDate() %></td>
			</tr>
			<tr>
				<th>Pickup Time:</th>
				<td><%=booking.getPickupTime() %></td>
			</tr>
			<tr>
				<th>DropOff Date:</th>
				<td><%=booking.getDropOffDate() %></td>
			</tr>
			<tr>
				<th>DropOff Time:</th>
				<td><%=booking.getDropOffTime() %></td>
			</tr>
			<tr>
				<th>Pickup Location:</th>
				<td><%=booking.getLocation() %></td>
			</tr>
		</table>	
	</div>
	<div>
		<table>
			<caption>Vehicle Details</caption>
			<tr>
				<th>Vehicle:</th>
				<td><%=vehicle.getName() %></td>
				<td rowspan="4"><img alt="vehicle.jpg" src="<%=vehicle.getImageUrl() %>" ></td>
			</tr>
			<tr>
				<th>Vehicle Type:</th>
				<td><%=vehicle.getvType() %></td>
			</tr>
			<tr>
				<th>Transmission Type:</th>
				<td><%=vehicle.gettType() %></td>
			</tr>
			<tr>
				<th>Air Conditioning:</th>
				<td>
				<% 
					if (vehicle.isAirConditioning() == true) {
				   	  out.print("Yes");
				   	} else {
					  out.print("No"); 
				   	}
				%>
				</td>
			</tr>
		</table>	
	</div>
	<div>
		<table>
			<caption>Driver Details</caption>
			<tr>
				<th>Driver Name:</th>
				<td><%=driver.getFirstName() + " " + driver.getLastName() %></td>
			</tr>
			<tr>
				<th>Driver License:</th>
				<td><%=driver.getLicense() %></td>
			</tr>
			<tr>
				<th>Driver Age:</th>
				<td><%=driver.getAge() %></td>
			</tr>
		</table>
	</div>
	<div>
		<a href="/BookingConfirmationServlet" ><input type="button" value="CONFIRM" ></a>
		<a href="/index.jsp" ><input type="button" value="CANCEL" ></a>
	</div>
	
</body>
</html>