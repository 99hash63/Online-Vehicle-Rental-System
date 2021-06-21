<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method="POST" action="UserConfirmationServlet">
		<fieldset>
			<legend style="font-size: 30px;"><b>Registration Form</b></legend>
<!-- 				<label>First Name:</label>  -->
				<input type = "text" name = "fName" placeholder="First Name" required><br/><br/>
<!-- 				<label>Last Name:</label>  -->
				<input type = "text" name = "lName" placeholder="Last Name"><br/><br/>
<!-- 				<label>Contact Number:</label>  -->
				<input type ="tel" name = "contact" pattern="07[0, 1, 2, 5, 6, 7, 8][0-9]+" maxlength="10" placeholder="Mobile Number: 07********" required><br/><br/>
<!-- 				<label>Email:</label> -->
				<input type = "email" name = "email" placeholder="User Email" required><br/><br/>
<!-- 				<label>Password:</label> -->
				<input type="password" name="pwd2" placeholder="User Password" ><br/><br/>
				<input type = "submit" value ="REGISTER" >

		</fieldset>
	</form>
</body>
</html>