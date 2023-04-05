<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.ArrayList,database.project.Room"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Renting</title>
</head>
<body>
Employee <%out.println(request.getAttribute("employeeSSN"));%><br>
<hr>

<%
ArrayList<Room> rooms = (ArrayList<Room>)request.getAttribute("rooms"); 
for (Room room : rooms) {%> 
	<form method="get" action="RentRoom">
<%
	out.println(room.toString());
%> 
	<input type="hidden" name="hotel_id" value="<%out.print(room.getRoomHotelID());%>">
	<input type="hidden" name="room_number" value="<%out.print(room.getRoomNumber());%>">
	<input type="hidden" name="employeeSSN" value="<%out.print(request.getAttribute("employeeSSN"));%>"><br>
	<label>Customer ID: <input type="text" name="custSSN" required="required"></label><br>
	<label>Start Date: <input type="date" name="start_date" required="required"></label><br>
	<label>End Date: <input type="date" name="end_date" required="required"></label><br>
	<label>Payment: <input type="text" name="payment" required="required"></label><br>
	<input type="submit" value="Rent">
	<hr>
	</form>
<%
}
%>
</body>
</html>