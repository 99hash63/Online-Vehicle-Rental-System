<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="web.model.Driver"%>
<%@page import="web.service.IDriverService"%>
<%@page import="web.service.DriverServiceImplementation"%>
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
				<th>Driver ID</th>
				<th>Driver First Name</th>
				<th>Driver Last Name</th>
				<th>Age</th>
				<th>NIC</th>
				<th>License</th>
				<th>Sex</th>
				<th>Availability</th>
				<th>Manage</th>
			</tr>
            <%
            	IDriverService iDriverService = new DriverServiceImplementation();
				ArrayList<Driver> driverList = iDriverService.getDrivers();
            
            	for (Driver driver : driverList) {
            		
            %>
            <tr>
            	<td><%= driver.getDriverID() %></td>
           		<td><%= driver.getFirstName() %></td>
            	<td><%= driver.getLastName() %></td>
            	<td><%= driver.getAge() %></td>           
            	<td><%= driver.getNIC() %></td>
            	<td><%= driver.getLicense() %></td>
            	<td><%= driver.getSex() %></td>
            	<td>
            		<% 
            			if (driver.isAvailability() == true) {
            				out.print("Yes");
            			} else {
            				out.print("No");
            			}
            		%>
            	</td>
            	<td>
            		<form method="POST" action="GetDriverServlet">
	            		<input type="hidden" value="<%= driver.getDriverID() %>" >
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