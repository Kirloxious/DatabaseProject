<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "java.util.ArrayList,database.project.RoomView"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Room Views</title>
</head>
<style>
table{
	float: left;
}
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
th, td{
  text-align: center;
}
</style>
<body>
<h1>Room Views</h1>
<%ArrayList<RoomView> hotelRoomCapacities = (ArrayList<RoomView>)request.getAttribute("RoomCapacities"); 
%>
<TABLE style="margin-right: 1em;">
	<caption>Hotel Room Capacities</caption>
	<tr>
		<th>Hotel ID</th>
		<th>Room Number</th>
		<th>Capacity</th>
	</tr>
	<% for(RoomView view: hotelRoomCapacities){ %>
		<tr>
			<td><%out.println(view.getHotelId()); %></td>
			<td><%out.println(view.getRoomNumber()); %></td>
			<td><%out.println(view.getCapacity()); %></td>
		</tr>
	<% }%>
</TABLE>

<%ArrayList<RoomView> availableRoomsByArea = (ArrayList<RoomView>)request.getAttribute("RoomsByArea"); 
%>
<TABLE style="">
<caption>Number of Available Rooms by Area</caption>
	<tr>
		<th>Area</th>
		<th>Hotel Chain ID</th>
		<th>Rating</th>
		<th>Number Of Rooms</th>
	</tr>
	<% for(RoomView view: availableRoomsByArea){ %>
		<tr>
			<td><%out.println(view.getArea()); %></td>
			<td><%out.println(view.getHotelChainId()); %></td>
			<td><%out.println(view.getStarRating()); %></td>
			<td><%out.println(view.getNumOfRooms()); %></td>
		</tr>
	<% }%>
</TABLE>


</body>
</html>