package database.project;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HotelManagmentServlet
 */
@WebServlet("/HotelManagment")
public class HotelManagmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hotel_action = request.getParameter("hotel_action");
		String phone_action = request.getParameter("phone_action");
		String add_phone = request.getParameter("add_phone");
		String add_hotel = request.getParameter("add_hotel");
		
		if (hotel_action != null) {
			hotelAction(hotel_action, request, response);
		} else if (phone_action != null) {
			phoneAction(phone_action, request, response);
		} else if (add_phone != null) {
			addPhone(request, response);
		} else if (add_hotel != null) {
			addHotel(request, response);
		} else {
			response.getWriter().append("No action specified.");
		}
	}

	private void addHotel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String hotel_chain_id = request.getParameter("hotel_chain_id");
		String star_rating = request.getParameter("star_rating");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		
		//TODO needs to add a manager ???

		boolean success = new MySQLConnection().addHotel(Integer.parseInt(hotel_chain_id), Integer.parseInt(star_rating), address, email);
		
		if (success) {
			response.getWriter().append("Hotel Added");
		} else {
			response.getWriter().append("Failed to add Hotel");
		}
	}

	private void addPhone(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String hotel_id = request.getParameter("hotel_id");
		String phone = request.getParameter("phone");
		
		boolean success = new MySQLConnection().addHotelPhone(Integer.parseInt(hotel_id), phone);
		
		if (success) {
			response.getWriter().append("Phone Added");
		} else {
			response.getWriter().append("Failed to add Phone");
		}
	}

	private void phoneAction(String phone_action, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String hotel_id = request.getParameter("hotel_id");
		String old_phone_number = request.getParameter("old_phone_number");
		String new_phone_number = request.getParameter("new_phone_number");
		
		boolean success;
		
		switch(phone_action) {	
		case "Update":
			success = new MySQLConnection().updateHotelPhone(Integer.parseInt(hotel_id), old_phone_number, new_phone_number);
			break;
		case "Delete":
			success = new MySQLConnection().deleteHotelPhone(Integer.parseInt(hotel_id), old_phone_number);
			break;
		default:
			response.getWriter().append("Invalid hotel action");
			return;
		}
		
		if (success) {
			response.getWriter().append("Phone: Successful ").append(phone_action);
		} else {
			response.getWriter().append("Phone: Failed ").append(phone_action);
		}
	}

	private void hotelAction(String hotel_action, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String hotel_id = request.getParameter("hotel_id");
		String hotel_chain_id = request.getParameter("hotel_chain_id");
		String star_rating = request.getParameter("star_rating");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		
		boolean success;
		
		switch (hotel_action) {
		case "Update":
			success = new MySQLConnection().updateHotel(Integer.parseInt(hotel_id), Integer.parseInt(hotel_chain_id), Integer.parseInt(star_rating), address, email);
			break;
		case "Delete":
			success = new MySQLConnection().deleteHotel(Integer.parseInt(hotel_id));
			break;
		case "Manage Rooms":
			request.setAttribute("hotel_id", hotel_id);
			request.getRequestDispatcher("employee/rooms.jsp").forward(request, response);
			return;
		default:
			response.getWriter().append("Invalid hotel action");
			return;
		}
		
		if (success) {
			response.getWriter().append("Hotel: Successful ").append(hotel_action);
		} else {
			response.getWriter().append("Hotel: Failed ").append(hotel_action);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
