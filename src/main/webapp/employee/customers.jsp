<%@page import="database.project.Customer,java.sql.Date,java.text.SimpleDateFormat"%>
<%@page import="database.project.MySQLConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Customers</title>
</head>
<body>
<%
	for (Customer c : new MySQLConnection().getAllCustomers()) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(c.getRegisteredDate());
		%>
		<form id="cust<%out.print(c.getSsn());%>" action="CustomerManger" method="get"></form>
		<input type="hidden" name="customer_ssn" value="<%out.print(c.getSsn());%>" form="cust<%out.print(c.getSsn());%>">
		<label>SSN: <input type="text" disabled="disabled" value="<%out.print(c.getSsn());%>"></label><br>
		<input type="submit" name="customer_action" value="Update" form="cust<%out.print(c.getSsn());%>">
		<input type="submit" name="customer_action" value="Delete" form="cust<%out.print(c.getSsn());%>"><br>
		<label>First Name: <input type="text" name="first_name" value="<%out.print(c.getFirstName());%>" form="cust<%out.print(c.getSsn());%>"></label><br>
		<label>Middle Name: <input type="text" name="middle_name" value="<%out.print(c.getMiddleName());%>" form="cust<%out.print(c.getSsn());%>"></label><br>
		<label>Last Name: <input type="text" name="last_name" value="<%out.print(c.getLastName());%>" form="cust<%out.print(c.getSsn());%>"></label><br>
		<label>Registered Date: <input type="date" value="<%out.print(date);%>" form="cust<%out.print(c.getSsn());%>"></label><br>
		<%
		int i = 0;
		for (String address : c.getAddressess()) {
			i++;
			%>
			<form id="cust_<%out.print(c.getSsn()+"_"+i);%>" action="CustomerManger" method="get"></form>
			<input type="hidden" name="customer_ssn" value="<%out.print(c.getSsn());%>" form="cust_<%out.print(c.getSsn()+"_"+i);%>">
			<input type="hidden" name="old_address" value="<%out.print(address);%>" form="cust_<%out.print(c.getSsn()+"_"+i);%>">
			<label>Address <%out.print(i);%>: <input type="text" name="new_address" value="<%out.print(address);%>" form="cust_<%out.print(c.getSsn()+"_"+i);%>"></label>
			<input type="submit" name="address_action" value="Update" form="cust_<%out.print(c.getSsn()+"_"+i);%>">
			<input type="submit" name="address_action" value="Delete" form="cust_<%out.print(c.getSsn()+"_"+i);%>">
			<%
		}
		%>
		<form id="add_address<%out.print(c.getSsn());%>" action="CustomerManger" method="get"></form>
		<input type="hidden" name="customer_ssn" value="<%out.print(c.getSsn());%>" form="add_address<%out.print(c.getSsn());%>">
		<label>New Address: <input type="text" name="address" form="add_address<%out.print(c.getSsn());%>"></label>
		<input type="submit" name="address" value="Add" form="add_address<%out.print(c.getSsn());%>">
		<hr>
		<%
	}
%>
<form id="add_customer" action="CustomerManger" method="get"></form>
<label>SSN: <input type="text" name="customer_ssn" form="add_customer"></label><br>
<label>First Name: <input type="text" name="first_name" form="add_customer"></label><br>
<label>Middle Name: <input type="text" name="middle_name" form="add_customer"></label><br>
<label>Last Name: <input type="text" name="last_name" form="add_customer"></label><br>
<input type="submit" name="add_customer" value="Add Customer" form="add_customer">
</body>
</html>