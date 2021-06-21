<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search</title>
</head>
<body>
	<div>
		<form method="POST" action="SearchVehicleServlet">
			<fieldset>
				<legend style="font-size: 30px;"><b>Search</b></legend>
				<label>Pickup Date:</label>
				<input type="date" name="pickupDate" ><br><br>
				<label>Pickup Time:</label>
				<input type="time" name="pickupTime" ><br><br>
				<label>DropOff Date:</label>
				<input type="date" name="dropOffDate" ><br><br>
				<label>DropOff Time:</label>
				<input type="time" name="dropOffTime" ><br><br>
				<label>Location:</label>
				<input type="text" name="location" ><br><br>
				<label>Vehicle Type:</label>
				<select name="type">
					<option value="car">Car</option>
					<option value="van">Van</option>
					<option value="bus">Bus</option>
				</select><br><br>
				<input type="submit" value="Search" >
			</fieldset>
		</form>
	</div>
</body>
</html>