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
@WebServlet("/CustomerRegisterServerlet")
public class CustomerRegisterServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CustomerRegisterServerlet() {
        // TODO Auto-generated constructor stub
    }

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
		
		boolean success = new MySQLConnection().insertCustomer(ssn, first_name, middle_name, last_name);
		
		if (success) {
			response.getWriter().append("Successfully added customer");
		} else {
			response.getWriter().append("Failed to add customer");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
