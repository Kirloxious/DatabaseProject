<%@ page import="java.util.ArrayList,database.project.Room" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Rooms</title>
</head>
<body>
Customer <%out.println(request.getAttribute("ssn"));%><br>
From <%out.print(request.getAttribute("start_date"));%> to <%out.print(request.getAttribute("end_date"));%><br>
<br>

<%
ArrayList<Room> rooms = (ArrayList<Room>)request.getAttribute("rooms"); 
for (Room room : rooms) {%> 
	<form method="get" action="BookRoom">
	<input type="hidden" name="ssn" value="<%out.print(request.getAttribute("ssn"));%>">
	<input type="hidden" name="hotel_id" value="<%out.print(room.getRoomHotelID());%>">
	<input type="hidden" name="room_number" value="<%out.print(room.getRoomNumber());%>">
	<input type="hidden" name="start_date" value="<%out.print(request.getAttribute("start_date"));%>">
	<input type="hidden" name="end_date" value="<%out.print(request.getAttribute("end_date"));%>">
<%
	out.println(room.toString());
%> 
	<input type="submit" value="Book">
	<hr>
	</form>
<%
}
%>
</body>
</html>