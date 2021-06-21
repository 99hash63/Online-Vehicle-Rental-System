<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="web.model.Admin"%>
<%@page import="web.service.IAdminService"%>
<%@page import="web.service.AdminServiceImplementation"%>
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
				<th>Administrator ID</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Contact Number</th>
				<th>User Email</th>
				<th>Remove</th>
				<th>Manage</th>
			</tr>
			<%
				String adminID = request.getParameter("adminID");
			
				IAdminService iAdminService = new AdminServiceImplementation();
				ArrayList<Admin> adminList = iAdminService.getAdmins();
				
				for (Admin admin : adminList) {
			%>
			<tr>
				<td><%= admin.getAdminID() %></td>
				<td><%= admin.getFirstName() %></td>
				<td><%= admin.getLastName() %></td>
				<td><%= admin.getContactNumber() %></td>
				<td><%= admin.getEmail() %></td>
				<td>
					<form method="POST" action="RemoveAdminServlet" >
						<input type="hidden" value="<%= admin.getAdminID() %>" >
						<input type="submit" value="Remove Administrator" >				
					</form>
				</td>
				<td>
					<%
						if (adminID.equals(admin.getAdminID())) {
							
					%>
						<form method="POST" action="GetAdminServlet" >
							<input type="hidden" value="<%= admin.getAdminID() %>" >
							<input type="submit" value="Select" >
						</form>
					<%
						} else {
							out.print("Unavailable");
						}
					%>
				</td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>