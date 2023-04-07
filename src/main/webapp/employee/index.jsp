<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Main Page</title>
</head>
<body>
Employee <%out.print(request.getAttribute("ssn"));%><br>
<h1>View Active Bookings and Rentings</h1>
<form action="BookingRenting" method="get">
<input type="hidden" name="ssn" value="<%out.print(request.getAttribute("ssn"));%>">
<label>Booking ID: <input type="text" name="booking_id"></label><br>
<label>Renting ID: <input type="text" name="renting_id"></label><br>
<label>Include Archived: <input type="checkbox" name="include_achived"></label><br>
<label>Start Date: <input type="date" name="start_date"></label><br>
<label>End Date: <input type="date" name="end_date"></label><br>
Room capacity: <label>Min <input type="text" name="min_room_capacity"></label><label> Max <input type="text" name="max_room_capacity"></label><br>
<label>Area: <input type="text" name="area"></label><br>
<label>Hotel Chain: <input type="text" name="hotel_chain"></label><br>
<label>Star Category: <input type="text" name="stars"></label><br>
Hotel Size: <label>Min <input type="text" name="min_hotel_size"></label><label> Max <input type="text" name="max_hotel_size"></label><br>
Price: <label>Min <input type="text" name="min_price"></label><label> Max <input type="text" name="max_price"></label><br>
<input type="submit" value="Search">
</form>
<hr>
<h1>Create Renting without Booking</h1>
<form action="NewRenting" method="get">
<input type="hidden" name="ssn" value="<%out.print(request.getAttribute("ssn"));%>">
<input type="submit" value="Create Renting">
</form>
<hr>
<h1>DB admin</h1>
<form action="DBAdmin" method="get">
<input type="hidden" name="ssn" value="<%out.print(request.getAttribute("ssn"));%>">
<input type="submit" name="submit" value="Manage Customers"><br>
<input type="submit" name="submit" value="Manage Employees"><br>
<input type="submit" name="submit" value="Manage Hotels"><br>
</form>
</body>
</html>