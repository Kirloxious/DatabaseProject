package database.project;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RentRoomServlet
 */
@WebServlet("/RentRoom")
public class RentRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String custSSN = request.getParameter("custSSN");
		String employeeSSN = request.getParameter("employeeSSN");
		String hotel_id = request.getParameter("hotel_id");
		String room_number = request.getParameter("room_number");
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		String bookingID = request.getParameter("bookingID");

		
		boolean success;
		if(bookingID != null) {
			success = new MySQLConnection().rentRoom(Integer.parseInt(custSSN), Integer.parseInt(room_number), Integer.parseInt(hotel_id), start_date, end_date, Integer.parseInt(bookingID), Integer.parseInt(employeeSSN));	
		}else {
			success = new MySQLConnection().rentRoom(Integer.parseInt(custSSN), Integer.parseInt(room_number), Integer.parseInt(hotel_id), start_date, end_date, Integer.parseInt(employeeSSN));	
		}
		
		if (success) {
			response.getWriter().append("Renting made.");
		} else {
			response.getWriter().append("Failed make renting.");
		}
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
