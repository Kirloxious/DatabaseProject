package database.project;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerLoginServerlet
 */
@WebServlet("/EmployeeRegisterServerlet")
public class EmployeeRegisterServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO need way to get list of addresses
		String ssn = request.getParameter("ssn");
		String first_name = request.getParameter("first_name");
		String middle_name = request.getParameter("middle_name");
		if (middle_name.equals("")) middle_name = null;
		String last_name = request.getParameter("last_name");
		String hotel_id = request.getParameter("hotel_id");
		
		boolean success = new MySQLConnection().insertEmployee(Integer.parseInt(ssn), Integer.parseInt(hotel_id), first_name, middle_name, last_name);
		
		if (success) {
			response.getWriter().append("Successfully added employee");
		} else {
			response.getWriter().append("Failed to add employee");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
