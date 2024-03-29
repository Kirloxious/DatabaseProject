package database.project;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NewRentingServerlet
 */
@WebServlet("/NewRenting")
public class NewRentingServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ssn = request.getParameter("ssn");
		
		ArrayList<Room> rooms = new MySQLConnection().getAllAvailableRooms(new ArrayList<String>(), null, null);
		
		if(rooms != null) {
			request.setAttribute("employeeSSN", ssn);
			request.setAttribute("rooms", rooms);
			request.getRequestDispatcher("employee/new-renting.jsp").forward(request, response);			
		}
		else {
			response.getWriter().append("Failed to get rooms");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
