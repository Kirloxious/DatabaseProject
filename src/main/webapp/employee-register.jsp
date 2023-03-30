<%@ page import="database.project.MySQLConnection" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Employee</title>
</head>
<body>
<form action="EmployeeRegisterServerlet" method="post">
<label>SSN: <input type="text" name="ssn" required="required"></label><br>
<label>HotelID: <select name="hotel_id">
  <% 
  	for (Integer hotelId : new MySQLConnection().getHotels()) {
  		%>
  		<option value="<%out.print(hotelId);%>"><%out.print(hotelId);%></option>
  		<%
  	}
  %>
</select></label><br>
<label>First Name: <input type="text" name="first_name" required="required"></label><br>
<label>Middle Name: <input type="text" name="middle_name"></label><br>
<label>Last Name: <input type="text" name="last_name" required="required"></label><br>
<input type="submit" value="register">
</form>
</body>
</html>