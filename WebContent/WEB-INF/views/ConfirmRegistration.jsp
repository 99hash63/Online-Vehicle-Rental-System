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
		<form method="POST" action="UserConfirmationServlet">
			<fieldset>
				<legend style="font-size: 30px"><b>Registered Details</b></legend>
<!-- 				<label>User Email:</label> -->
				<input type="email" name="email" placeholder="User Email" ><br><br>
<!-- 				<label>Password:</label> -->
				<input type="password" name="pwd1" placeholder="User Password"><br><br>
				<input type="submit" value="Confirm" >
			
			</fieldset>
		</form>
	</div><br><br>
	<div>
		<p><b>Register as a New Customer. Click the below link</b></p>
		<a href="/WEB-INF/views/Registration.jsp">New Customer Registration</a>
	</div>
</body>
</html>