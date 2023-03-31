<%@page import="database.project.Employee"%>
<%@page import="database.project.MySQLConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Employees</title>
</head>
<body>
<%
	for (Employee e : new MySQLConnection().getAllEmployees()) {
		%>
		<form id="employee<%out.print(e.getSsn());%>" action="EmployeeManger" method="get"></form>
		<input type="hidden" name="employee_ssn" value="<%out.print(e.getSsn());%>" form="employee<%out.print(e.getSsn());%>">
		<label>SSN: <input type="text" disabled="disabled" value="<%out.print(e.getSsn());%>"></label><br>
		<input type="submit" name="employee_action" value="Update" form="employee<%out.print(e.getSsn());%>">
		<input type="submit" name="employee_action" value="Delete" form="employee<%out.print(e.getSsn());%>"><br>
		<label>Works For: <input type="text" name="hotel_id" value="<%out.print(e.getHotelID());%>" form="employee<%out.print(e.getSsn());%>"></label><br>
		<label>First Name: <input type="text" name="first_name" value="<%out.print(e.getFirstName());%>" form="employee<%out.print(e.getSsn());%>"></label><br>
		<label>Middle Name: <input type="text" name="middle_name" value="<%out.print(e.getMiddleName());%>" form="employee<%out.print(e.getSsn());%>"></label><br>
		<label>Last Name: <input type="text" name="last_name" value="<%out.print(e.getLastName());%>" form="employee<%out.print(e.getSsn());%>"></label><br>

		<%
		int i = 0;
		for (String address : e.getAddressess()) {
			i++;
			%>
			<form id="employee_<%out.print(e.getSsn()+"_"+i);%>" action="EmployeeManger" method="get"></form>
			<input type="hidden" name="employee_ssn" value="<%out.print(e.getSsn());%>" form="employee_<%out.print(e.getSsn()+"_"+i);%>">
			<input type="hidden" name="old_address" value="<%out.print(address);%>" form="employee_<%out.print(e.getSsn()+"_"+i);%>">
			<label>Address <%out.print(i);%>: <input type="text" name="new_address" value="<%out.print(address);%>" form="employee_<%out.print(e.getSsn()+"_"+i);%>"></label>
			<input type="submit" name="address_action" value="Update" form="employee_<%out.print(e.getSsn()+"_"+i);%>">
			<input type="submit" name="address_action" value="Delete" form="employee_<%out.print(e.getSsn()+"_"+i);%>">
			<%
		}
		%>
		<form id="add_address<%out.print(e.getSsn());%>" action="EmployeeManger" method="get"></form>
		<input type="hidden" name="employee_ssn" value="<%out.print(e.getSsn());%>" form="add_address<%out.print(e.getSsn());%>">
		<label>New Address: <input type="text" name="address" form="add_address<%out.print(e.getSsn());%>"></label>
		<input type="submit" name="add_address" value="Add" form="add_address<%out.print(e.getSsn());%>">
		
		<%
		i = 0;
		for (String role : e.getRoles()) {
			i++;
			%>
			<form id="employee_<%out.print(e.getSsn()+"_"+i+"_role");%>" action="EmployeeManger" method="get"></form>
			<input type="hidden" name="employee_ssn" value="<%out.print(e.getSsn());%>" form="employee_<%out.print(e.getSsn()+"_"+i+"_role");%>">
			<input type="hidden" name="old_role" value="<%out.print(role);%>" form="employee_<%out.print(e.getSsn()+"_"+i+"_role");%>">
			<label>Role <%out.print(i);%>: <input type="text" name="new_role" value="<%out.print(role);%>" form="employee_<%out.print(e.getSsn()+"_"+i+"_role");%>"></label>
			<input type="submit" name="role_action" value="Update" form="employee_<%out.print(e.getSsn()+"_"+i+"_role");%>">
			<input type="submit" name="role_action" value="Delete" form="employee_<%out.print(e.getSsn()+"_"+i+"_role");%>">
			<%
		}
		%>
		<form id="add_role<%out.print(e.getSsn());%>" action="EmployeeManger" method="get"></form>
		<input type="hidden" name="employee_ssn" value="<%out.print(e.getSsn());%>" form="add_role<%out.print(e.getSsn());%>">
		<label>New Role: <input type="text" name="role" form="add_role<%out.print(e.getSsn());%>"></label>
		<input type="submit" name="add_role" value="Add" form="add_role<%out.print(e.getSsn());%>">
		<hr>
		<%
	}
%>
<form id="add_employee" action="EmployeeManger" method="get"></form>
<label>SSN: <input type="text" name="employee_ssn" form="add_employee"></label><br>
<label>HotelID: <select name="hotel_id" form="add_employee">
  <% 
  	for (Integer hotelId : new MySQLConnection().getHotels()) {
  		%>
  		<option value="<%out.print(hotelId);%>"><%out.print(hotelId);%></option>
  		<%
  	}
  %>
</select></label><br>
<label>First Name: <input type="text" name="first_name" form="add_employee"></label><br>
<label>Middle Name: <input type="text" name="middle_name" form="add_employee"></label><br>
<label>Last Name: <input type="text" name="last_name" form="add_employee"></label><br>
<input type="submit" name="add_employee" value="Add employee" form="add_employee">
</body>
</html>