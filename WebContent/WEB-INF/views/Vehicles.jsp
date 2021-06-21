<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="web.model.Vehicle"%>
<%@page import="web.service.IVehicleService"%>
<%@page import="web.service.VehicleServiceImplementation"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form method="POST" action="/WEB-INF/views/AddVehicle.jsp" >
			<label>Add a new Vehicle </label>
			<input type="submit" value="Add Vehicle" >
		</form>
	</div>
	<div>
		<table>
            <%
            	IVehicleService iVehicleService = new VehicleServiceImplementation();
				ArrayList<Vehicle> vehicleList = iVehicleService.getVehicle();
            
            	for (Vehicle vehicle : vehicleList) {
            		
            %>
            <tr>
            	<td rowspan="9" >
            		<img alt="vehicle.jpg" src="<%= vehicle.getImageUrl() %>" >
            	</td>
            	<td>Vehicle ID: <%= vehicle.getVehicleID() %></td>
            </tr>
            <tr>
            	<td>Vehicle Name: <%= vehicle.getName() %></td>
            </tr>
            <tr>
            	<td>Vehicle Type: <%= vehicle.getvType() %></td>
            </tr>
            <tr>
            	<td>Transmission Type: <%= vehicle.gettType() %></td>
            </tr>
            <tr>
            	<td>Air Conditioning: 
            	<% 
            		if (vehicle.isAirConditioning() == true) {
            			out.print("Yes");
            		} else {
            			out.print("No");
            		}
            	%>
            	</td>
            </tr>
            <tr>
            	<td>Availability: 
            	<% 
            		if (vehicle.isAvailability() == true) {
            			out.print("Yes");
            		} else {
            			out.print("No");
            		}
            	%>
            	</td>
            </tr>
            <tr>
            	<td>Vehicle Image URL: <%= vehicle.getImageUrl() %></td>
            </tr>
            <tr>
            	<td>Rate Per Day: <%= System.out.format("%.2f", vehicle.getRatePerDay()) %></td>
            </tr>
            <tr>
            	<td>
            		<form method="POST" action="GetVehicleServlet">
	            		<input type="hidden" name="vehicleID" value="<%= vehicle.getVehicleID() %>" >
	            		<input type="submit" value="SELECT" >
	            	</form>
	            </td>
            </tr>	
            <%
            	}
            %>
		</table>
	</div>
</body>
</html>