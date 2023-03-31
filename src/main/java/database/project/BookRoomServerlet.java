package database.project;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookRoomServerlet
 */
@WebServlet("/BookRoom")
public class BookRoomServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ssn = request.getParameter("ssn");
		String hotel_id = request.getParameter("hotel_id");
		String room_number = request.getParameter("room_number");
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		
		boolean success = new MySQLConnection().bookRoom(Integer.parseInt(ssn), Integer.parseInt(room_number), Integer.parseInt(hotel_id), start_date, end_date);
		
		if (success) {
			response.getWriter().append("Booking made.");
		} else {
			response.getWriter().append("Failed make booking.");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
