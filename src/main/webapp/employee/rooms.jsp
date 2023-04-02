<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Rooms</title>
</head>
<body>
list of rooms
button update/delete
Given hotelId display all rooms
HotelID int Not Null,
RoomNumber int Not Null,
Price Decimal(10,2) Check (Price > 0),
Capacity int Check (Capacity > 0),
View Varchar(50) Check (View in ("Mountain", "Sea")),
Extentable bool Not Null,

button update/delete problem
List string roomProblems
add problem

update/delete ammenitie
list string ammenities
add ammenitie

add room
</body>
</html>