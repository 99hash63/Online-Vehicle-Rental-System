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
		<form method="POST" action="CustomerConfirmationServlet" >
			<input type="text" name="email" placeholder="User Email" ><br><br>
			<input type="text" name="password" placeholder="User Password" ><br><br>
			<input type="submit" value="CONFIRM" >	
		</form>
	</div>
</body>
</html>