package database.project;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RoomManagementServelet
 */
@WebServlet("/RoomManagement")
public class RoomManagementServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String room_action = request.getParameter("room_action");
		String problem_action = request.getParameter("problem_action");
		String add_problem = request.getParameter("add_problem");
		String amenetie_action = request.getParameter("amenetie_action");
		String add_amenetie = request.getParameter("add_amenetie");
		String add_room = request.getParameter("add_room");
		
		if (room_action != null) {
			roomAction(room_action, request, response);
		} else if (problem_action != null) {
			problemAction(problem_action, request, response);
		} else if (add_problem != null) {
			addProblem(request, response);
		}  else if (amenetie_action != null) {
			amenetieAction(amenetie_action, request, response);
		} else if (add_amenetie != null) {
			addAmenetie(request, response);
		} else if (add_room != null) {
			addRoom(request, response);
		} else {
			response.getWriter().append("No action specified.");
		}
	}

	private void addRoom(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String room_number = request.getParameter("room_number");
		String hotel_id = request.getParameter("hotel_id");
		String price = request.getParameter("price");
		String capacity = request.getParameter("capacity");
		String view = request.getParameter("view");
		String extendable = request.getParameter("extendable");
		
		boolean success = new MySQLConnection().addRoom(Integer.parseInt(hotel_id), Integer.parseInt(room_number), price, Integer.parseInt(capacity), view, extendable!=null);
		
		if (success) {
			response.getWriter().append("Room Added");
		} else {
			response.getWriter().append("Failed to add Room");
		}
	}

	private void addAmenetie(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String room_number = request.getParameter("room_number");
		String hotel_id = request.getParameter("hotel_id");
		String amenetie = request.getParameter("amenetie");
		
		boolean success = new MySQLConnection().addRoomAmenetie(Integer.parseInt(hotel_id), Integer.parseInt(room_number), amenetie);
		
		if (success) {
			response.getWriter().append("Amenetie Added");
		} else {
			response.getWriter().append("Failed to add Amenetie");
		}
	}

	private void amenetieAction(String amenetie_action, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String room_number = request.getParameter("room_number");
		String hotel_id = request.getParameter("hotel_id");
		String old_amenetie = request.getParameter("old_amenetie");
		String new_amenetie = request.getParameter("new_amenetie");
		
		boolean success;
		
		switch (amenetie_action) {
		case "Update":
			success = new MySQLConnection().updateRoomAmenetie(Integer.parseInt(hotel_id), Integer.parseInt(room_number), old_amenetie, new_amenetie);
			break;
		case "Delete":
			success = new MySQLConnection().deleteRoomAmenetie(Integer.parseInt(hotel_id), Integer.parseInt(room_number), old_amenetie);
			break;
		default:
			response.getWriter().append("Invalid Amenetie Action");
			return;
		}
		
		if (success) {
			response.getWriter().append("Amenetie ").append(amenetie_action);
		} else {
			response.getWriter().append("Amenetie: Failed to ").append(amenetie_action);
		}
	}

	private void addProblem(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String room_number = request.getParameter("room_number");
		String hotel_id = request.getParameter("hotel_id");
		String problem = request.getParameter("problem");

		
		boolean success = new MySQLConnection().addRoomProblem(Integer.parseInt(hotel_id), Integer.parseInt(room_number), problem);
		
		if (success) {
			response.getWriter().append("Problem Added");
		} else {
			response.getWriter().append("Failed to add Problem");
		}
	}

	private void problemAction(String problem_action, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String room_number = request.getParameter("room_number");
		String hotel_id = request.getParameter("hotel_id");
		String old_problem = request.getParameter("old_problem");
		String new_problem = request.getParameter("new_problem");
		
		boolean success;
		
		switch (problem_action) {
		case "Update":
			success = new MySQLConnection().updateRoomProblem(Integer.parseInt(hotel_id), Integer.parseInt(room_number), old_problem, new_problem);
			break;
		case "Delete":
			success = new MySQLConnection().deleteRoomProblem(Integer.parseInt(hotel_id), Integer.parseInt(room_number), old_problem);
			break;
		default:
			response.getWriter().append("Invalid Problem Action");
			return;
		}
		
		if (success) {
			response.getWriter().append("Problem ").append(problem_action);
		} else {
			response.getWriter().append("Problem: Failed to ").append(problem_action);
		}
	}

	private void roomAction(String room_action, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String room_number = request.getParameter("room_number");
		String hotel_id = request.getParameter("hotel_id");
		String price = request.getParameter("price");
		String capacity = request.getParameter("capacity");
		String view = request.getParameter("view");
		String extendable = request.getParameter("extendable");
		
		boolean success;
		
		switch (room_action) {
		case "Update":
			success = new MySQLConnection().updateRoom(Integer.parseInt(hotel_id), Integer.parseInt(room_number), price, Integer.parseInt(capacity), view, extendable!=null);
			break;
		case "Delete":
			success = new MySQLConnection().deleteRoom(Integer.parseInt(hotel_id), Integer.parseInt(room_number));
			break;
		default:
			response.getWriter().append("Invalid Room Action");
			return;
		}
		
		if (success) {
			response.getWriter().append("Room ").append(room_action);
		} else {
			response.getWriter().append("Room: Failed to ").append(room_action);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
