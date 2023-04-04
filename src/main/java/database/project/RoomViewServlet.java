package database.project;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RoomViewServlet
 */
@WebServlet("/RoomView")
public class RoomViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<RoomView> availableRoomsByArea = new MySQLConnection().getAvailableRoomsByAreaView();
		ArrayList<RoomView> hotelRoomCapacities = new MySQLConnection().getHotelRoomCapacitiesView();
		
		if(availableRoomsByArea != null && hotelRoomCapacities != null) {
			request.setAttribute("RoomCapacities", hotelRoomCapacities);
			request.setAttribute("RoomsByArea", availableRoomsByArea);
			request.getRequestDispatcher("customer/room-views.jsp").forward(request, response);
		}else {
			response.getWriter().append("Failed to get rooms");
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
