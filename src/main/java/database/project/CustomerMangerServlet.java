package database.project;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerMangerServlet
 */
@WebServlet("/CustomerManger")
public class CustomerMangerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customer_action = request.getParameter("customer_action");
		String address_action = request.getParameter("address_action");
		String add_address = request.getParameter("add_address");
		String add_customer = request.getParameter("add_customer");
		
		
		if (customer_action != null) {
			customerAction(customer_action, request, response);
		} else if (address_action != null) {
			addressAction(address_action, request, response);
		} else if (add_customer != null) {
			addCustomer(request, response);
		} else if (add_address != null) {
			addAddress(request, response);
		} else {
			response.getWriter().append("No action specified.");
		}
	}

	private void addAddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String customer_ssn = request.getParameter("customer_ssn");
		String address = request.getParameter("address");
		
		boolean success = new MySQLConnection().addCustomerAddress(Integer.parseInt(customer_ssn), address);
		if (success) {
			response.getWriter().append("Added Address");
		} else {
			response.getWriter().append("Failed to add Address");
		}
	}

	private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String customer_ssn = request.getParameter("customer_ssn");
		String first_name = request.getParameter("first_name");
		String middle_name = request.getParameter("middle_name");
		String last_name = request.getParameter("last_name");
		
		boolean success = new MySQLConnection().insertCustomer(customer_ssn, first_name, middle_name, last_name);
		
		if (success) {
			response.getWriter().append("Added Customer");
		} else {
			response.getWriter().append("Failed to add Customer");
		}
	}

	private void addressAction(String address_action, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String customer_ssn = request.getParameter("customer_ssn");
		String old_address = request.getParameter("old_address");
		String new_address = request.getParameter("new_address");
		
		if (address_action.equals("Update")) {
			boolean success = new MySQLConnection().updateCustomerAddress(Integer.parseInt(customer_ssn), old_address, new_address);
			if (success) {
				response.getWriter().append("Updated address");
			} else {
				response.getWriter().append("Failed to add update address");
			}
		} else if (address_action.equals("Delete")) {				
			boolean success = new MySQLConnection().deleteCustomerAddress(Integer.parseInt(customer_ssn), old_address);
			if (success) {
				response.getWriter().append("Deleted address");
			} else {
				response.getWriter().append("Failed to add delete address");
			}
		} else {
			response.getWriter().append("Invalid address action");
		}
		
	}

	private void customerAction(String action, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String customer_ssn = request.getParameter("customer_ssn");
		String first_name = request.getParameter("first_name");
		String middle_name = request.getParameter("middle_name");
		String last_name = request.getParameter("last_name");
		String date = request.getParameter("date");
		if (action.equals("Update")) {	
			boolean success = new MySQLConnection().updateCustomer(Integer.parseInt(customer_ssn), first_name, middle_name, last_name, date);
			if (success) {
				response.getWriter().append("Updated customer");
			} else {
				response.getWriter().append("Failed to update customer");
			}
		} else if (action.equals("Delete")) {	
			boolean success = new MySQLConnection().deleteCustomer(Integer.parseInt(customer_ssn));
			if (success) {
				response.getWriter().append("Deleted customer");
			} else {
				response.getWriter().append("Failed to delete customer");
			}
		} else {
			response.getWriter().append("Invalid customer action");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
