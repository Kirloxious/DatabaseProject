package database.project;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RoomSearchServerlet
 */
@WebServlet("/RoomSearch")
public class RoomSearchServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ssn = request.getParameter("ssn");
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		String min_room_capacity = request.getParameter("min_room_capacity");
		String max_room_capacity = request.getParameter("max_room_capacity");
		String area = request.getParameter("area");
		String hotel_chain = request.getParameter("hotel_chain");
		String stars = request.getParameter("stars");
		String min_hotel_size = request.getParameter("min_hotel_size");
		String max_hotel_size = request.getParameter("max_hotel_size");
		String min_price = request.getParameter("min_price");
		String max_price = request.getParameter("max_price");
		
		//TODO filter
		ArrayList<Room> rooms = new MySQLConnection().getAllAvailableRooms();
		
		if (rooms != null) {
			request.setAttribute("ssn", ssn);
			request.setAttribute("rooms", rooms);
			request.setAttribute("start_date", start_date);
			request.setAttribute("end_date", end_date);
			request.getRequestDispatcher("customer/room-results.jsp").forward(request, response);
		} else {
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
