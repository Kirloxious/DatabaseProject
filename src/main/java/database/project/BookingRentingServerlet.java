package database.project;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookingRentingServerlet
 */
@WebServlet("/BookingRenting")
public class BookingRentingServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public BookingRentingServerlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ssn = request.getParameter("ssn");
		String booking_id = request.getParameter("booking_id");
		String renting_id = request.getParameter("renting_id");
		String include_achived = request.getParameter("include_achived");
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
		
		/* TODO
		 * get bookings, get rentings and pass to jsp
		 */
		request.getRequestDispatcher("employee/bookings-rentings.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
