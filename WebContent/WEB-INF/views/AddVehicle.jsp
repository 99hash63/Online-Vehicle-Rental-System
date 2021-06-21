<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form method="POST" action="AddVehicleServlet">
			<fieldset>
				<legend>New Vehicle</legend>
				<input type="text" name="vName" placeholder="Vehicle Name" required><br><br>
				<label>Vehicle Type: </label>
				<select name="vType" required>
						<option value="car">Car</option>
						<option value="van">Van</option>
						<option value="bus">Bus</option>
				</select><br><br>
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
				<input type="text" name="vImage" placeholder="Vehicle Image URL" required><br><br>
				<input type="text" name="vRate" placeholder="Rate Per Day" required><br><br>
				<input type="submit" value="Add Vehicle" >
				<input type="reset" value="Reset" >
			</fieldset>
		</form>
	</div>
</body>
</html>