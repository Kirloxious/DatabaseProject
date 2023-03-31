package database.project;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class employeeMangerServlet
 */
@WebServlet("/EmployeeManger")
public class EmployeeMangerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String employee_action = request.getParameter("employee_action");
		String address_action = request.getParameter("address_action");
		String add_address = request.getParameter("add_address");
		String add_employee = request.getParameter("add_employee");
		String role_action = request.getParameter("role_action");
		String add_role = request.getParameter("add_role");
		
		if (employee_action != null) {
			employeeAction(employee_action, request, response);
		} else if (address_action != null) {
			addressAction(address_action, request, response);
		} else if (add_employee != null) {
			addEmployee(request, response);
		} else if (add_address != null) {
			addAddress(request, response);
		} else if (role_action != null) {
			roleAction(role_action, request, response);
		} else if (add_role != null) { 
			addRole(request, response);
		} else {
			response.getWriter().append("No action specified.");
		}
	}

	private void addAddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String employee_ssn = request.getParameter("employee_ssn");
		String address = request.getParameter("address");
		
		boolean success = new MySQLConnection().addEmployeeAddress(Integer.parseInt(employee_ssn), address);
		if (success) {
			response.getWriter().append("Added Address");
		} else {
			response.getWriter().append("Failed to add Address");
		}
	}

	private void addRole(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String employee_ssn = request.getParameter("employee_ssn");
		String role = request.getParameter("role");
		
		boolean success = new MySQLConnection().addEmployeeRole(Integer.parseInt(employee_ssn), role);
		if (success) {
			response.getWriter().append("Added Role");
		} else {
			response.getWriter().append("Failed to add Role");
		}
	}
	
	private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String employee_ssn = request.getParameter("employee_ssn");
		String hotel_id = request.getParameter("hotel_id");
		String first_name = request.getParameter("first_name");
		String middle_name = request.getParameter("middle_name");
		String last_name = request.getParameter("last_name");
		
		boolean success = new MySQLConnection().insertEmployee(Integer.parseInt(employee_ssn), Integer.parseInt(hotel_id), first_name, middle_name, last_name);
		
		if (success) {
			response.getWriter().append("Added employee");
		} else {
			response.getWriter().append("Failed to add employee");
		}
	}

	private void addressAction(String address_action, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String employee_ssn = request.getParameter("employee_ssn");
		String old_address = request.getParameter("old_address");
		String new_address = request.getParameter("new_address");
		
		if (address_action.equals("Update")) {
			boolean success = new MySQLConnection().updateEmployeeAddress(Integer.parseInt(employee_ssn), old_address, new_address);
			if (success) {
				response.getWriter().append("Updated address");
			} else {
				response.getWriter().append("Failed to add update address");
			}
		} else if (address_action.equals("Delete")) {				
			boolean success = new MySQLConnection().deleteEmployeeAddress(Integer.parseInt(employee_ssn), old_address);
			if (success) {
				response.getWriter().append("Deleted address");
			} else {
				response.getWriter().append("Failed to add delete address");
			}
		} else {
			response.getWriter().append("Invalid address action");
		}
		
	}
	
	private void roleAction(String role_action, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String employee_ssn = request.getParameter("employee_ssn");
		String old_role = request.getParameter("old_role");
		String new_role = request.getParameter("new_role");
		
		if (role_action.equals("Update")) {
			boolean success = new MySQLConnection().updateEmployeerole(Integer.parseInt(employee_ssn), old_role, new_role);
			if (success) {
				response.getWriter().append("Updated role");
			} else {
				response.getWriter().append("Failed to add update role");
			}
		} else if (role_action.equals("Delete")) {				
			boolean success = new MySQLConnection().deleteEmployeeRole(Integer.parseInt(employee_ssn), old_role);
			if (success) {
				response.getWriter().append("Deleted role");
			} else {
				response.getWriter().append("Failed to add delete role");
			}
		} else {
			response.getWriter().append("Invalid role action");
		}
		
	}

	private void employeeAction(String action, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String employee_ssn = request.getParameter("employee_ssn");
		String hotel_id = request.getParameter("hotel_id");
		String first_name = request.getParameter("first_name");
		String middle_name = request.getParameter("middle_name");
		String last_name = request.getParameter("last_name");
		String date = request.getParameter("date");
		if (action.equals("Update")) {	
			boolean success = new MySQLConnection().updateEmployee(Integer.parseInt(employee_ssn), hotel_id, first_name, middle_name, last_name, date);
			if (success) {
				response.getWriter().append("Updated cutomer");
			} else {
				response.getWriter().append("Failed to update employee");
			}
		} else if (action.equals("Delete")) {	
			boolean success = new MySQLConnection().deleteEmployee(Integer.parseInt(employee_ssn));
			if (success) {
				response.getWriter().append("Deleted employee");
			} else {
				response.getWriter().append("Failed to delete employee");
			}
		} else {
			response.getWriter().append("Invalid employee action");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
