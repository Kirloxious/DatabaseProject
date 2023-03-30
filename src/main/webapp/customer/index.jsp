<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Main Page</title>
</head>
<body>
Customer <%out.print(request.getAttribute("ssn"));%>

<form action="RoomSearch" method="post">
<input type="hidden" name="ssn" value="<%out.print(request.getAttribute("ssn"));%>">
<label>Start Date: <input type="date" name="start_date" required="required"></label><br>
<label>End Date: <input type="date" name="end_date" required="required"></label><br>
Room capacity: <label>Min <input type="text" name="min_room_capacity"></label><label> Max <input type="text" name="max_room_capacity"></label><br>
<label>Area: <input type="text" name="area"></label><br>
<label>Hotel Chain: <input type="text" name="hotel_chain"></label><br>
<label>Star Category: <input type="text" name="stars"></label><br>
Hotel Size: <label>Min <input type="text" name="min_hotel_size"></label><label> Max <input type="text" name="max_hotel_size"></label><br>
Price: <label>Min <input type="text" name="min_price"></label><label> Max <input type="text" name="max_price"></label><br>
<input type="submit" value="Search"</label>
</form>
</body>
</html>