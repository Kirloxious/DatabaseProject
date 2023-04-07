<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.ArrayList,database.project.Booking,database.project.Renting"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bookings and Rentings</title>
</head>
<body>
Employee <%out.print(request.getAttribute("ssn"));%><br>
<h1>All Active Bookings</h1>
<hr>
<%
ArrayList<Booking> bookings = (ArrayList<Booking>)request.getAttribute("bookings"); 
for (Booking booking : bookings) {%> 
	<form method="get" action="RentRoom">
	<input type="hidden" name="employeeSSN" value="<%out.print(request.getAttribute("ssn"));%>">
	<input type="hidden" name="hotel_id" value="<%out.print(booking.getHotelID());%>">
	<input type="hidden" name="room_number" value="<%out.print(booking.getRoomNumber());%>">
	<input type="hidden" name="start_date" value="<%out.print(booking.getStartDate());%>">
	<input type="hidden" name="end_date" value="<%out.print(booking.getEndDate());%>">
	<input type="hidden" name="custSSN" value="<%out.print(booking.getCustomerSSN());%>">
	<input type="hidden" name="bookingID" value="<%out.print(booking.getBookingID());%>">
<%
	out.println(booking.toString());
%> 
	<input type="submit" value="Rent">
	</form>
	<form method="get" action="ArchiveRoom">
	<input type="hidden" name="rentingID" value="nil">
	<input type="hidden" name="bookingID" value="<%out.print(booking.getBookingID());%>">
	<input type="submit" value="Archive">
	</form>
	<hr>
<%
}
%>

<h1>All Active Rentings</h1>
<hr>
<%
ArrayList<Renting> rentings = (ArrayList<Renting>)request.getAttribute("rentings"); 
for (Renting renting: rentings) {%> 
	<form method="get" action="ArchiveRoom">
	<input type="hidden" name="rentingID" value="<%out.print(renting.getRentingID());%>">
	<% if (renting.getBookingID() != null) {%>
	<input type="hidden" name="bookingID" value="<%out.print(renting.getBookingID());%>">
	<%}%>
<%
	out.println(rentings.toString());
%> 
	<input type="submit" value="Archive">
	</form>
	<hr>
<%
}
%>
</body>
</html>