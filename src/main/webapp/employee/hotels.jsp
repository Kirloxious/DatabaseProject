<%@page import="database.project.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Hotels</title>
</head>
<body>
<% 
	for (Hotel h : new MySQLConnection().getAllHotels()) { //How to deal with manager constraints ?
		%> 
		<form action="HotelManagment" method="get" id="hotel_<%out.print(h.getHotelID());%>"></form>
		Hotel Id: <%out.print(h.getHotelID());%><br>		
		<input type="hidden" name="hotel_id" value="<%out.print(h.getHotelID());%>" form="hotel_<%out.print(h.getHotelID());%>">
		<input type="submit" name="hotel_action" value="Update" form="hotel_<%out.print(h.getHotelID());%>">
		<input type="submit" name="hotel_action" value="Delete" form="hotel_<%out.print(h.getHotelID());%>">
		<input type="submit" name="hotel_action" value="Manage Rooms" form="hotel_<%out.print(h.getHotelID());%>"><br>
		<label>Hotel Chain ID: <input type="text" name="hotel_chain_id" value="<%out.print(h.getHotelChainID());%>" form="hotel_<%out.print(h.getHotelID());%>"></label><br>
		<label>Number of Rooms: <input type="text" disabled="disabled" name="number_of_rooms" value="<%out.print(h.getNumberOfRooms());%>" form="hotel_<%out.print(h.getHotelID());%>"></label><br>
		<label>Star Rating: <select name="star_rating" form="hotel_<%out.print(h.getHotelID());%>">
		<% 
			for (int i = 1; i<=5; i++){
				if (h.getStarRating() == i) {
					%><option selected="selected" value="<%out.print(i);%>"><%out.print(i);%></option><%
				} else {
					%><option value="<%out.print(i);%>"><%out.print(i);%><%
				}
			}
		%>
		</select></label><br>
		<label>Address: <input type="text" name="address" value="<%out.print(h.getAddress());%>" form="hotel_<%out.print(h.getHotelID());%>"></label><br>
		<label>Email: <input type="text" name="email" value="<%out.print(h.getEmail());%>" form="hotel_<%out.print(h.getHotelID());%>"></label><br>
		
		<%
			int i = 0;
			for (String phoneNumber : h.getPhoneNumbers()) {
				i++;
				%>
				<form action="HotelManagment" method="get" id="hotel_<%out.print(h.getHotelID()+"_"+phoneNumber);%>"></form>
				<input type="hidden" name="hotel_id" value="<%out.print(h.getHotelID());%>" form="hotel_<%out.print(h.getHotelID()+"_"+phoneNumber);%>">
				<input type="hidden" name="old_phone_number" value="<%out.print(phoneNumber);%>" form="hotel_<%out.print(h.getHotelID()+"_"+phoneNumber);%>">
				<label>Phone Number <%out.print(i);%>: <input type="text" name="new_phone_number" value="<%out.print(phoneNumber);%>" form="hotel_<%out.print(h.getHotelID()+"_"+phoneNumber);%>"></label>
				<input type="submit" name="phone_action" value="Update" form="hotel_<%out.print(h.getHotelID()+"_"+phoneNumber);%>">
				<input type="submit" name="phone_action" value="Delete" form="hotel_<%out.print(h.getHotelID()+"_"+phoneNumber);%>"><br>
				<%
			}
			%> 
			<form action="HotelManagment" method="get" id="hotel_<%out.print(h.getHotelID());%>_add_phone"></form>
			<input type="hidden" name="hotel_id" value="<%out.print(h.getHotelID());%>" form="hotel_<%out.print(h.getHotelID());%>_add_phone">
			<label>New Phone Number: <input type="text" name="phone" form="hotel_<%out.print(h.getHotelID());%>_add_phone"></label>
			<input type="submit" name="add_phone" value="Add" form="hotel_<%out.print(h.getHotelID());%>_add_phone">
			<hr>
			<%
	}
	%>
	<form action="HotelManagment" method="get" id="add_hotel"></form>
	<label>Hotel Chain ID: <input type="text" name="hotel_chain_id" form="add_hotel"></label><br>
	<label>Star Rating: <select name="star_rating" form="add_hotel">
	<% 
		for (int i = 1; i<=5; i++){
			%><option value="<%out.print(i);%>"><%out.print(i);%></option><%
		}
	%>
	</select></label><br>
	<label>Address: <input type="text" name="address" form="add_hotel"></label><br>
	<label>Email: <input type="text" name="email" form="add_hotel"></label>
	<input type="submit" name="add_hotel" value="Add Hotel" form="add_hotel"><br>
	<%
	
%>
</body>
</html>