package database.project;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ArchiveRoomServlet
 */
@WebServlet("/ArchiveRoom")
public class ArchiveRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookingID = request.getParameter("bookingID");
		String rentingID = request.getParameter("rentingID");
		boolean success;
		//only archive renting
		if(bookingID.equals("nil")) {
			success = new MySQLConnection().ArchiveRoomRenting(Integer.parseInt(rentingID));
		}
		//only archive booking
		else if(rentingID.equals("nil")) {
			success = new MySQLConnection().ArchiveRoomBooking(Integer.parseInt(bookingID));
		}
		else {
			success = new MySQLConnection().ArchiveRoom(Integer.parseInt(bookingID), Integer.parseInt(rentingID));			
		}
		
		if (success) {
			response.getWriter().append("Room arhived.");
		} else {
			response.getWriter().append("Failed to archive room.");
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
