<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="web.model.Vehicle"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		Vehicle vehicle = (Vehicle) request.getAttribute("vehicle");
	%>
	<div>
		<form method="POST" action="UpdateVehicleServlet" >
			<fieldset>
				<legend>Manage Vehicle</legend>
				<label>Vehicle ID: </label><br>
				<input type="text" name="vID" value="<%= vehicle.getVehicleID() %>" disabled="disabled" ><br><br>
				<label>Vehicle Name: </label><br>
				<input type="text" name="vName" value="<%= vehicle.getName() %>" disabled="disabled" ><br><br>
				<label>Vehicle Type: </label><br>
				<input type="text" name="vType" value="<%= vehicle.getvType() %>" disabled="disabled" ><br><br>
				<label>Transmission Type: </label>
				<select name="tType" required>
						<option value="auto">Automatic</option>
						<option value="manual">Manual</option>
				</select><br><br>
				<label>Air Conditioning: </label>
				<select name="AC" required>
						<option value="true">Yes</option>
						<option value="false">No</option>
				</select><br><br>
				<label>Availability: </label>
				<select name="available" required>
						<option value="true">Yes</option>
						<option value="false">No</option>
				</select><br><br>
				<label>Vehicle Image URL: </label><br>
				<input type="text" name="vImage" value="<%= vehicle.getImageUrl() %>" required><br><br>
				<label>Rate per Day: </label><br>
				<input type="text" name="vRate" value="<%= vehicle.getRatePerDay() %>"required ><br><br>
				<input type="submit" value="Update Vehicle" >
			</fieldset>
		</form>
	</div>
	<div>
		<form method="POST" action="RemoveVehicleServlet">
			<label>Remove this Vehicle from Database </label>
			<input type="hidden" name="vID" value="<%= vehicle.getVehicleID() %>" >
			<input type="submit" value="Remove Vehicle" >
		</form>
	</div>
</body>
</html>