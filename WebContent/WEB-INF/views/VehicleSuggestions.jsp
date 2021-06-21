<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="web.model.Vehicle"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th colspan="4" >Vehicle</th>
            <th>Description</th>
		</tr>
		<%	@SuppressWarnings("unchecked")
			ArrayList<Vehicle> arrayVehicle = (ArrayList<Vehicle>) request.getAttribute("array");
			
			for (Vehicle vehicle : arrayVehicle){		
		%>
			<tr>
				<td rowspan="5">
					<form method="POST" action="SearchVehicleServlet">
						<input type="hidden" name="vehicleID" value="<%=vehicle.getVehicleID()%>">
						<input type="image" src="<%=vehicle.getImageUrl() %>" alt="click image" >
					</form>
				</td>
				<td>Vehicle: <%=vehicle.getName() %> </td>
			</tr>
			<tr>
				<td>Type: <%=vehicle.getvType() %> </td>
			</tr>
			<tr>
				<td>Transmission: <%=vehicle.gettType() %> </td>
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
				<td>Charges Per Day: LKR <%=System.out.format("%.2f", vehicle.getRatePerDay()) %> </td>
			</tr>
									
		<%
			}
		%>	
	
	</table>
</body>
</html>