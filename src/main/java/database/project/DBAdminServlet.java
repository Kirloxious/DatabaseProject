package database.project;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DBAdminServlet
 */
@WebServlet("/DBAdmin")
public class DBAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String manage = request.getParameter("submit");
		String ssn = request.getParameter("ssn");
		
		request.setAttribute("ssn", ssn);
		switch (manage) {
		case "Manage Customers":
			request.getRequestDispatcher("employee/customers.jsp").forward(request, response);
			return;
		case "Manage Employees":
			request.getRequestDispatcher("employee/employees.jsp").forward(request, response);
			return;
		case "Manage Hotels":
			request.getRequestDispatcher("employee/hotels.jsp").forward(request, response);
			return;
		}
		
		response.getWriter().append("Invalid option");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
