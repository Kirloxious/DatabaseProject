<%@page import="database.project.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Rooms</title>
</head>
<body>
<%
for (Room r : new MySQLConnection().getRoomsInHotel(Integer.parseInt((String)request.getAttribute("hotel_id")))){
	%>
	<form action="RoomManagement" id="room<%out.print(r.getRoomNumber());%>" method="get"></form>
	<input type="hidden" name="room_number" value="<%out.print(r.getRoomNumber());%>" form="room<%out.print(r.getRoomNumber());%>">
	<input type="hidden" name="hotel_id" value="<%out.print(r.getRoomHotelID());%>" form="room<%out.print(r.getRoomNumber());%>">
	<% out.print("Hotel: " + r.getRoomHotelID() + " Room: " + r.getRoomNumber()); %><br>
	<input type="submit" name="room_action" value="Update" form="room<%out.print(r.getRoomNumber());%>">
	<input type="submit" name="room_action" value="Delete" form="room<%out.print(r.getRoomNumber());%>"><br>
	<label>Price: <input type="text" name="price" value="<%out.print(r.getRoomPrice());%>" form="room<%out.print(r.getRoomNumber());%>"></label><br>
	<label>Capacity: <input type="text" name="capacity" value="<%out.print(r.getRoomCapacity());%>" form="room<%out.print(r.getRoomNumber());%>"></label><br>
	<label>View: <select name="view" form="room<%out.print(r.getRoomNumber());%>">
	<% 
	if (r.getRoomView().equals("Mountain")) {
		%>
		<option value="Mountain" selected="selected">Mountain</option>
		<option value="Sea">Sea</option>
		<%
	} else {
		%>
		<option value="Mountain">Mountain</option>
		<option value="Sea" selected="selected">Sea</option>
		<%
	}
	%>
	</select></label><br>
	<label>Extendable: <input type="checkbox" name="extendable" <%if(r.isExtentable()){out.print("checked");}%> form="room<%out.print(r.getRoomNumber());%>"></label><br>
	<%
	int i=0;
	for (String p : r.getRoomProblems()) {
		i++;
		%>
		<form action="RoomManagement" method="get" id="prob<%out.print(r.getRoomNumber()+"_"+i);%>"></form>
		<input type="hidden" name="room_number" value="<%out.print(r.getRoomNumber());%>" form="prob<%out.print(r.getRoomNumber()+"_"+i);%>">
		<input type="hidden" name="hotel_id" value="<%out.print(r.getRoomHotelID());%>" form="prob<%out.print(r.getRoomNumber()+"_"+i);%>">
		<input type="hidden" name="old_problem" value="<%out.print(p);%>" form="prob<%out.print(r.getRoomNumber()+"_"+i);%>">
		<label>Problem <%out.print(i);%>: <input type="text" name="new_problem" value="<%out.print(p);%>" form="prob<%out.print(r.getRoomNumber()+"_"+i);%>"></label>
		<input type="submit" name="problem_action" value="Update" form="prob<%out.print(r.getRoomNumber()+"_"+i);%>">
		<input type="submit" name="problem_action" value="Delete" form="prob<%out.print(r.getRoomNumber()+"_"+i);%>"><br>
		<%
	}
	%>
	<form action="RoomManagement" method="get" id="add_prob<%out.print(r.getRoomNumber());%>"></form>
	<input type="hidden" name="room_number" value="<%out.print(r.getRoomNumber());%>" form="add_prob<%out.print(r.getRoomNumber());%>">
	<input type="hidden" name="hotel_id" value="<%out.print(r.getRoomHotelID());%>" form="add_prob<%out.print(r.getRoomNumber());%>">
	<label>Add Problem: <input type="text" name="problem" form="add_prob<%out.print(r.getRoomNumber());%>"></label>
	<input type="submit" name="add_problem" value="Add" form="add_prob<%out.print(r.getRoomNumber());%>">
	
	<%
	i=0;
	for (String p : r.getRoomAmeneties()) {
		i++;
		%>
		<form action="RoomManagement" method="get" id="amend<%out.print(r.getRoomNumber()+"_"+i);%>"></form>
		<input type="hidden" name="room_number" value="<%out.print(r.getRoomNumber());%>" form="amend<%out.print(r.getRoomNumber()+"_"+i);%>">
		<input type="hidden" name="hotel_id" value="<%out.print(r.getRoomHotelID());%>" form="amend<%out.print(r.getRoomNumber()+"_"+i);%>">
		<input type="hidden" name="old_amenetie" value="<%out.print(p);%>" form="amend<%out.print(r.getRoomNumber()+"_"+i);%>">
		<label>Amenetie <%out.print(i);%>: <input type="text" name="new_amenetie" value="<%out.print(p);%>" form="amend<%out.print(r.getRoomNumber()+"_"+i);%>"></label>
		<input type="submit" name="amenetie_action" value="Update" form="amend<%out.print(r.getRoomNumber()+"_"+i);%>">
		<input type="submit" name="amenetie_action" value="Delete" form="amend<%out.print(r.getRoomNumber()+"_"+i);%>"><br>
		<%
	}
	%>
	<form action="RoomManagement" method="get" id="add_amend<%out.print(r.getRoomNumber());%>"></form>
	<input type="hidden" name="room_number" value="<%out.print(r.getRoomNumber());%>" form="add_amend<%out.print(r.getRoomNumber());%>">
	<input type="hidden" name="hotel_id" value="<%out.print(r.getRoomHotelID());%>" form="add_amend<%out.print(r.getRoomNumber());%>">
	<label>Add Amenetie: <input type="text" name="amenetie" form="add_amend<%out.print(r.getRoomNumber());%>"></label>
	<input type="submit" name="add_amenetie" value="Add" form="add_amend<%out.print(r.getRoomNumber());%>">
	
	<hr>
	<%
}
%>

<form action="RoomManagement" id="add_room" method="get"></form>
<label>Room number: <input type="text" name="room_number" form="add_room"></label><br>
<input type="hidden" name="hotel_id" value="<%out.print(request.getAttribute("hotel_id"));%>" form="add_room">
<label>Price: <input type="text" name="price" form="add_room"></label><br>
<label>Capacity: <input type="text" name="capacity" form="add_room"></label><br>
<label>View: <select name="view" form="add_room">
	<option value="Mountain">Mountain</option>
	<option value="Sea">Sea</option>
</select></label><br>
<label>Extendable: <input type="checkbox" name="extendable" form="add_room"></label><br>
<input type="submit" name="add_room" value="Add" form="add_room">

</body>
</html>