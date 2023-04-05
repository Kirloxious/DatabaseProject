package database.project;

import java.io.IOException;
import java.util.ArrayList;

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
		
		ArrayList<String> c = new ArrayList<>();
		if (!booking_id.equals("")) c.add("b.BookingID="+booking_id);
		if (include_achived == null) c.add("NOT EXISTS(SELECT * FROM archived WHERE b.BookingID = archived.BookingID)");
		
		if (!start_date.equals("")) c.add("b.StartDate='"+start_date+"'");
		if (!end_date.equals("")) c.add("b.EndDate='"+end_date+"'");
		if (!min_room_capacity.equals("")) c.add("r.Capacity>=" + min_room_capacity);
		if (!max_room_capacity.equals("")) c.add("r.Capacity<=" + max_room_capacity);
		if (!area.equals("")) c.add("h.Address LIKE '%"+area+"%'");
		if (!hotel_chain.equals("")) c.add("h.HotelChainID="+hotel_chain);
		if (!stars.equals("")) c.add("h.StarRating="+stars);
		if (!min_hotel_size.equals("")) c.add("h.NumberOfRooms>="+min_hotel_size);
		if (!max_hotel_size.equals("")) c.add("h.NumberOfRooms<="+max_hotel_size);
		if (!min_price.equals("")) c.add("r.Price>="+min_price);
		if (!max_price.equals("")) c.add("r.Price<="+max_price);
		
		/* TODO
		 * get bookings, get rentings and pass to jsp
		 */
		ArrayList<Booking> bookings= new MySQLConnection().getAllActiveBookings(c);
		
		c = new ArrayList<>();
		if (!renting_id.equals("")) c.add("b.RentingID="+renting_id);
		if (include_achived == null) c.add("NOT EXISTS(SELECT * FROM archived WHERE b.RentingID = archived.RentingID)");
		if (!start_date.equals("")) c.add("b.StartDate='"+start_date+"'");
		if (!end_date.equals("")) c.add("b.EndDate='"+end_date+"'");
		if (!min_room_capacity.equals("")) c.add("r.Capacity>=" + min_room_capacity);
		if (!max_room_capacity.equals("")) c.add("r.Capacity<=" + max_room_capacity);
		if (!area.equals("")) c.add("h.Address LIKE '%"+area+"%'");
		if (!hotel_chain.equals("")) c.add("h.HotelChainID="+hotel_chain);
		if (!stars.equals("")) c.add("h.StarRating="+stars);
		if (!min_hotel_size.equals("")) c.add("h.NumberOfRooms>="+min_hotel_size);
		if (!max_hotel_size.equals("")) c.add("h.NumberOfRooms<="+max_hotel_size);
		if (!min_price.equals("")) c.add("r.Price>="+min_price);
		if (!max_price.equals("")) c.add("r.Price<="+max_price);

		ArrayList<Renting> rentings = new MySQLConnection().getAllActiveRentings(c);
		if (bookings != null && rentings != null) {
			request.setAttribute("ssn", ssn);
			request.setAttribute("bookings", bookings);
			request.setAttribute("rentings", rentings);
			request.getRequestDispatcher("employee/bookings-rentings.jsp").forward(request, response);			
		} else {
			response.getWriter().append("Failed to get bookings");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
