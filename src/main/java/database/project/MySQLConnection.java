package database.project;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class MySQLConnection {

	Connection db = null;
	PreparedStatement ps = null;
    ResultSet rs = null;
    Statement st = null;
    String sql;
	
	public MySQLConnection() {
		
	}
	
	/**
	 * Opens a connection to the DB.
	 * */
	public void getConn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			db = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/e-hotels", "root", "rootlogin123");
		}catch (SQLException e){
            System.out.println("Error in connecting to MySQL server");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			System.out.println("SQL Driver not Found.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes the connection to the DB.
	 * */
	public void closeDB() {
		try {
			if(rs != null){
				rs.close();
			}
			if(ps!=null){
				ps.close();
			}
			if(st!=null){
				st.close();
			}
			if(db!=null){
				db.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Inserts new customer into the database
	 * */
	public boolean insertCustomer(String ssn, String firstName, String middleName, String lastName) {
		getConn();
		
		 try{//TODO address
			 	String sql = "insert into Customer values(?,?,?,?,?)";
			 	ps = db.prepareStatement(sql);
			 	ps.setString(1, ssn);
	        	ps.setString(2, firstName);
	        	ps.setString(3, middleName);
	        	ps.setString(4, lastName);
	        	ps.setDate(5, new Date(System.currentTimeMillis()));
	        	//insert customer
	        	ps.execute();
	        	System.out.print(sql);
	        	
	        	//insert customer's address
	        	/*
	        	sql = "insert into CustomerAddress values("+params[0]+",'"+params[4]+"')";
	        	System.out.print(sql);
	            st.executeUpdate(sql);
	            */
	            return true;

	        }catch(SQLException e){
	            e.printStackTrace();
	            return false;
	        }finally {
	        	closeDB();
	        }	       
		
	}
	
	/**
	 * Verfifies if a customer with the SSN past as argument exists in the DB.
	 * @param SSN Customer's SSN
	 * */
	public boolean verifyCustomerSSN(String SSN){
		getConn();
		
        try{
            ps = db.prepareStatement("select SSN from Customer where SSN=?");
            
            ps.setString(1, SSN);	               
            rs = ps.executeQuery();
			return rs.next();
            
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }finally {
        	closeDB();
        }
    }
	
	public ArrayList<Customer> getAllCustomers() {
		getConn();
		
		ArrayList<Customer> out = new ArrayList<>();
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		try {
			ps = db.prepareStatement("SELECT * FROM Customer");
			rs = ps.executeQuery();
			while (rs.next()) {
				Customer c = new Customer();
				c.setSsn(rs.getInt("SSN"));
				c.setRegisteredDate(rs.getDate("RegisterDate"));
				c.setFirstName(rs.getString("FirstName"));
				c.setMiddleName(rs.getString("MiddleName"));
				c.setLastName(rs.getString("LastName"));
				
				ps2 = db.prepareStatement("SELECT Address FROM CustomerAddress WHERE SSN=?");
				ps2.setInt(1, c.getSsn());
				rs2 = ps2.executeQuery();
				while (rs2.next()) {
					c.addAddress(rs2.getString("Address"));
				}
				out.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
	    } finally {
			try {
				if (ps2 != null) ps2.close();
				if (rs2 != null) rs2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	closeDB();
	    }
		
		return out;
	}
	
	
	/**
	 * Inserts new employee into the database
	 * */
	public boolean insertEmployee(Integer ssn, Integer hotelID, String firstName, String middleName, String lastName) {
		getConn();
		
		 try{ //TODO role and address insertion use transaction
			 	sql = "insert into Employee values (?,?,?,?,?)";
	        	ps = db.prepareStatement(sql);
	        	//insert employee
	        	ps.setInt(1, ssn);
	        	ps.setInt(2, hotelID);
	        	ps.setString(3, firstName);
	        	ps.setString(4, middleName);
	        	ps.setString(5, lastName);
	        	System.out.println(sql);
	        	ps.execute();
	        	/*
	        	//insert emlpoyee's role
	        	sql = "insert into Role values("+params[0]+",'"+params[5]+"')";
	        	System.out.println(sql);
	            st.executeUpdate(sql);
	            
	            
	            //insert emlpoyee's Address
	        	sql = "insert into EmployeeAddress values("+params[0]+",'"+params[6]+"')";
	        	System.out.println(sql);
	            st.executeUpdate(sql);
	            */
	            return true;

	        }catch(SQLException e){
	            e.printStackTrace();
	            return false;
	        }finally {
	        	closeDB();
	        }	       
		
	}
	
	/**
	 * Verfifies if an employee with the SSN past as argument exists in the DB.
	 * @param SSN Employee's SSN
	 * */
	public boolean verifyEmployeeSSN(String SSN){
		getConn();
		
        try{
            ps = db.prepareStatement("select SSN from Employee where SSN=?");
            
            ps.setString(1, SSN);	               
            rs = ps.executeQuery();
			return rs.next();
            
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }finally {
        	closeDB();
        }
    }
	
	public ArrayList<Employee> getAllEmployees() {
		getConn();
		
		ArrayList<Employee> out = new ArrayList<>();
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		try {
			ps = db.prepareStatement("SELECT * FROM Employee");
			rs = ps.executeQuery();
			while (rs.next()) {
				Employee e = new Employee();
				e.setSsn(rs.getInt("SSN"));
				e.setHotelID(rs.getInt("HotelID"));
				e.setFirstName(rs.getString("FirstName"));
				e.setMiddleName(rs.getString("MiddleName"));
				e.setLastName(rs.getString("LastName"));
				
				ps2 = db.prepareStatement("SELECT Address FROM EmployeeAddress WHERE SSN=?");
				ps2.setInt(1, e.getSsn());
				rs2 = ps2.executeQuery();
				while (rs2.next()) {
					e.addAddress(rs2.getString("Address"));
				}
				ps2.close();
				rs2.close();
				
				ps2 = db.prepareStatement("SELECT RoleTitle FROM Role WHERE SSN=?");
				ps2.setInt(1, e.getSsn());
				rs2 = ps2.executeQuery();
				while (rs2.next()) {
					e.addRole(rs2.getString("RoleTitle"));
				}
				ps2.close();
				rs2.close();
				
				out.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
	    } finally {
			try {
				if (ps2 != null) ps2.close();
				if (rs2 != null) rs2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	closeDB();
	    }
		
		return out;
	}
	
	
	/**
	 * Returns all the rooms which do not have a booking or renting currently.
	 * */
	public ArrayList<Room> getAllAvailableRooms(){ //TODO redo
		getConn();
		
		ArrayList<Room> Rooms = new ArrayList<Room>();
		
		try {
			ps = db.prepareStatement("SELECT room.HotelID, room.RoomNumber, room.Price, room.Capacity, room.View, room.Extentable FROM room\r\n"
					+ "WHERE NOT EXISTS(\r\n"
					+ "    SELECT Booking.HotelID, Booking.RoomNumber FROM Booking \r\n"
					+ "    WHERE room.HotelID = Booking.HotelID AND room.RoomNumber = Booking.RoomNumber\r\n"
					+ "    AND NOT EXISTS(SELECT Archived.BookingID FROM Archived WHERE Booking.BookingID = Archived.BookingID)\r\n"
					+ "    ) AND\r\n"
					+ "    NOT EXISTS(\r\n"
					+ "        SELECT Renting.HotelID, Renting.RoomNumber FROM Renting \r\n"
					+ "        WHERE room.HotelID = Renting.HotelID AND room.RoomNumber = Renting.RoomNumber\r\n"
					+ "        AND NOT EXISTS(SELECT Archived.RentingID FROM Archived WHERE Renting.RentingID = Archived.RentingID)\r\n"
					+ "    )");
			rs = ps.executeQuery();
			while(rs.next()){
				int roomHotelID = rs.getInt("HotelID");
				int roomNumber = rs.getInt("RoomNumber");
				double roomPrice = rs.getDouble("Price");
				int roomCapacity = rs.getInt("Capacity");
				String roomView = rs.getString("View");
				boolean extentable = rs.getBoolean("Extentable");
				Room room = new Room(roomNumber, roomHotelID, roomPrice, roomCapacity, roomView, extentable);
				
				//TODO Need a second query to get amenities and problems
				
				Rooms.add(room);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
        	closeDB();
        }
					
		return Rooms;

	}
	
	/**
	 * Creates a booking and inserts in the DB.
	 * 
	 * */
	public boolean bookRoom(Integer custSSN, Integer roomNumber, Integer roomHotelID, String startDate, String endDate) {
		getConn();
		
		try{
        	
        	ps = db.prepareStatement("INSERT INTO Booking(StartDate, RoomNumber, HotelID, Customer, EndDate)"
        			+ " VALUES ('"+startDate+"', "+roomNumber+","+roomHotelID+", "+custSSN+", '"+endDate+"')");
        	System.out.println(ps);
        	ps.execute();
			
            
            return true;

        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
		
	}
	
	/**
	 * Creates a renting with the customers booking and inserts in the DB.
	 * 
	 * */
	public boolean rentRoom(Integer custSSN, Integer roomNumber, Integer roomHotelID, String startDate, String endDate, Integer BookingID, Integer employeeSSN) {
		getConn();
		try{
        	
        	ps = db.prepareStatement("INSERT INTO Renting(StartDate, RoomNumber, HotelID, Customer, EndDate, CheckedInByEmployeeID, BookingID) VALUES ('"+startDate+"', "+roomNumber+","+roomHotelID+", "+custSSN+", '"+endDate+"', "+employeeSSN+", "+BookingID+")");
			System.out.println(ps);
        	ps.execute();
			
            
            return true;

        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
		
	}
	
	/**
	 * Creates a renting and inserts in the DB. For customers who don't have a booking.
	 * 
	 * */
	public boolean rentRoom(Integer custSSN, Integer roomNumber, Integer roomHotelID, String startDate, String endDate, Integer employeeSSN) {
		getConn();
		try{
        	
        	ps = db.prepareStatement("INSERT INTO Renting(StartDate, RoomNumber, HotelID, Customer, EndDate, CheckedInByEmployeeID) VALUES ('"+startDate+"', "+roomNumber+","+roomHotelID+", "+custSSN+", '"+endDate+"', "+employeeSSN+")");
			System.out.println(ps);
			ps.execute();
			
            
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}
	
	public ArrayList<Integer> getHotels() {
		getConn();
		ArrayList<Integer> out = new ArrayList<>();
		try{
			String sql = "SELECT HotelID FROM Hotel";
        	ps = db.prepareStatement(sql);
			System.out.println(sql);
        	rs = ps.executeQuery();
            
        	while (rs.next()) {
        		out.add(rs.getInt("HotelID"));
        	}
        	
            return out;
        }catch(SQLException e){
            e.printStackTrace();
            return null;	 
        }finally {
        	closeDB();
        }
	}
	
	public ArrayList<Hotel> getAllHotels() {
		getConn();
		ArrayList<Hotel> out = new ArrayList<>();
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		
		try {
			String sql = "SELECT * FROM Hotel";
			ps = db.prepareStatement(sql);
			System.out.println(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Hotel h = new Hotel();
				h.setAddress(rs.getString("Address"));
				h.setEmail(rs.getString("ContactEmail"));
				h.setHotelChainID(rs.getInt("HotelChainID"));
				h.setHotelID(rs.getInt("HotelID"));
				h.setNumberOfRooms(rs.getInt("NumberOfRooms"));
				h.setStarRating(rs.getInt("StarRating"));
				
				ps2 = db.prepareStatement("SELECT PhoneNumber FROM HotelPhoneNumber WHERE HotelID=?");
				ps2.setInt(1, h.getHotelID());
				
				rs2 = ps2.executeQuery();
				while (rs2.next()) {
					h.addPhoneNumber(rs2.getString("PhoneNumber"));
				}
				
				out.add(h);
				
				ps2.close();
				rs2.close();
			}
		} catch(SQLException e){
            e.printStackTrace();
            return null;	 
        }finally {
        	try {
        		if (ps2 != null) ps2.close();
        		if (rs2 != null) rs2.close();
        	} catch (SQLException e) {}
        	closeDB();
        }
		
		return out;
	}

	public boolean addCustomerAddress(int ssn, String address) {
		getConn();
		try{
			String sql = "INSERT INTO CustomerAddress VALUES (?,?)";
        	ps = db.prepareStatement(sql);
        	ps.setInt(1, ssn);
        	ps.setString(2, address);
			System.out.println(sql);
        	ps.execute();
        	
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}

	public boolean deleteCustomerAddress(int ssn, String address) {
		getConn();
		try{
			String sql = "DELETE FROM CustomerAddress WHERE SSN=? AND Address=?";
        	ps = db.prepareStatement(sql);
        	ps.setInt(1, ssn);
        	ps.setString(2, address);
			System.out.println(sql);
        	ps.execute();
        	
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}

	public boolean updateCustomerAddress(int ssn, String oldAddress, String newAddress) {
		getConn();
		try{
			String sql = "UPDATE CustomerAddress SET Address=? WHERE SSN=? AND Address=?";
        	ps = db.prepareStatement(sql);
        	ps.setString(1, newAddress);
        	ps.setInt(2, ssn);
        	ps.setString(3, oldAddress);
			System.out.println(sql);
        	ps.execute();
        	
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}

	public boolean updateCustomer(int ssn, String first_name, String middle_name, String last_name, String date) {
		getConn();
		try{
			String sql = "UPDATE Customer SET FirstName=?, MiddleName=?, LastName=?, RegisterDate=? WHERE SSN=?";
        	ps = db.prepareStatement(sql);
        	ps.setString(1, first_name);
        	ps.setString(2, middle_name);
        	ps.setString(3, last_name);
        	ps.setString(4, date);
        	ps.setInt(5, ssn);
			System.out.println(sql);
        	ps.execute();
        	
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}

	public boolean deleteCustomer(int ssn) {
		getConn();
		try{
			String sql = "DELETE FROM Customer WHERE SSN=?";
        	ps = db.prepareStatement(sql);
        	ps.setInt(1, ssn);
			System.out.println(sql);
        	ps.execute();
        	
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}

	public boolean addEmployeeAddress(int ssn, String address) {
		getConn();
		try{
			String sql = "INSERT INTO EmployeeAddress VALUES (?,?)";
        	ps = db.prepareStatement(sql);
        	ps.setInt(1, ssn);
        	ps.setString(2, address);
			System.out.println(sql);
        	ps.execute();
        	
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}

	public boolean updateEmployeeAddress(int ssn, String old_address, String new_address) {
		getConn();
		try{
			String sql = "UPDATE EmployeeAddress SET Address=? WHERE SSN=? AND Address=?";
        	ps = db.prepareStatement(sql);
        	ps.setString(1, new_address);
        	ps.setInt(2, ssn);
        	ps.setString(3, old_address);
			System.out.println(sql);
        	ps.execute();
        	
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}

	public boolean deleteEmployeeAddress(int ssn, String address) {
		getConn();
		try{
			String sql = "DELETE FROM EmployeeAddress WHERE SSN=? AND Address=?";
        	ps = db.prepareStatement(sql);
        	ps.setInt(1, ssn);
        	ps.setString(2, address);
			System.out.println(sql);
        	ps.execute();
        	
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}

	public boolean updateEmployee(int ssn, int hotel_id, String first_name, String middle_name,
			String last_name, String date) {
		getConn();
		try{
			String sql = "UPDATE Employee SET HotelID=?, FirstName=?, MiddleName=?, LastName=? WHERE SSN=?";
        	ps = db.prepareStatement(sql);
        	ps.setInt(1, hotel_id);
        	ps.setString(2, first_name);
        	ps.setString(3, middle_name);
        	ps.setString(4, last_name);
        	ps.setInt(5, ssn);
			System.out.println(sql);
        	ps.execute();
        	
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}

	public boolean deleteEmployee(int ssn) {
		getConn();
		try{
			String sql = "DELETE FROM Employee WHERE SSN=?";
        	ps = db.prepareStatement(sql);
        	ps.setInt(1, ssn);
			System.out.println(sql);
        	ps.execute();
        	
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}

	public boolean updateEmployeerole(int ssn, String old_role, String new_role) {
		getConn();
		try{
			String sql = "UPDATE Role SET RoleTitle=? WHERE SSN=? AND RoleTitle=?";
        	ps = db.prepareStatement(sql);
        	ps.setString(1, new_role);
        	ps.setInt(2, ssn);
        	ps.setString(3, old_role);
			System.out.println(sql);
        	ps.execute();
        	
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}

	public boolean deleteEmployeeRole(int ssn, String role) {
		getConn();
		try{
			String sql = "DELETE FROM Role WHERE SSN=? AND RoleTitle=?";
        	ps = db.prepareStatement(sql);
        	ps.setInt(1, ssn);
        	ps.setString(2, role);
			System.out.println(sql);
        	ps.execute();
        	
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}

	public boolean addEmployeeRole(int ssn, String role) {
		getConn();
		try{
			String sql = "INSERT INTO Role VALUES (?,?)";
        	ps = db.prepareStatement(sql);
        	ps.setInt(1, ssn);
        	ps.setString(2, role);
			System.out.println(sql);
        	ps.execute();
        	
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}

	public boolean updateHotel(int hotelID, int hotelChainID, int starRating, String address, String email) {
		getConn();
		try{
			String sql = "UPDATE Hotel SET HotelChainID=?, StarRating=?, Address=?, ContactEmail=? WHERE HotelID=?";
        	ps = db.prepareStatement(sql);
        	ps.setInt(1, hotelChainID);
        	ps.setInt(2, starRating);
        	ps.setString(3, address);
        	ps.setString(4, email);
        	ps.setInt(5, hotelID);
			System.out.println(sql);
        	ps.execute();
        	
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}

	public boolean deleteHotel(int hotelID) {
		getConn();
		try{
			String sql = "DELETE FROM Hotel WHERE HotelID=?";
        	ps = db.prepareStatement(sql);
        	ps.setInt(1, hotelID);
			System.out.println(sql);
        	ps.execute();
        	
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}

	public boolean deleteHotelPhone(int hotelID, String phone) {
		getConn();
		try{
			String sql = "DELETE FROM HotelPhoneNumber WHERE HotelID=? AND PhoneNumber=?";
        	ps = db.prepareStatement(sql);
        	ps.setInt(1, hotelID);
        	ps.setString(2, phone);
			System.out.println(sql);
        	ps.execute();
        	
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}

	public boolean updateHotelPhone(int hotelID, String old_phone_number, String new_phone_number) {
		getConn();
		try{
			String sql = "UPDATE HotelPhoneNumber SET PhoneNumber=? WHERE HotelID=? AND PhoneNumber=?";
        	ps = db.prepareStatement(sql);
        	ps.setString(1, new_phone_number);
        	ps.setInt(2, hotelID);
        	ps.setString(3, old_phone_number);
			System.out.println(sql);
        	ps.execute();
        	
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}

	public boolean addHotelPhone(int hotelID, String phone) {
		getConn();
		try{
			String sql = "INSERT INTO HotelPhoneNumber VALUES (?,?)";
        	ps = db.prepareStatement(sql);
        	ps.setInt(1, hotelID);
        	ps.setString(2, phone);
			System.out.println(sql);
        	ps.execute();
        	
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}

	public boolean addHotel(int hotelChainID, int starRating, String address, String email) {
		//TODO dealing with manager constraint
		getConn();
		try{
			String sql = "INSERT INTO Hotel (HotelChainID, NumberOfRooms, StarRating, Address, ContactEmail) VALUES (?,0,?,?,?)";
        	ps = db.prepareStatement(sql);
        	ps.setInt(1, hotelChainID);
        	ps.setInt(2, starRating);
        	ps.setString(3, address);
        	ps.setString(4, email);
			System.out.println(sql);
        	ps.execute();
        	
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}
	
	public ArrayList<Room> getRoomsInHotel(int hotelID) {
		getConn();
		ArrayList<Room> out = new ArrayList<>();
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		
		try {
			String sql = "SELECT * FROM Room WHERE HotelID=?";
			ps = db.prepareStatement(sql);
			ps.setInt(1, hotelID);
			System.out.println(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Room r = new Room();
				r.setExtentable(rs.getBoolean("Extentable"));
				r.setRoomCapacity(rs.getInt("Capacity"));
				r.setRoomHotelID(rs.getInt("HotelID"));
				r.setRoomNumber(rs.getInt("RoomNumber"));
				r.setRoomPrice(rs.getDouble("Price"));
				r.setRoomView(rs.getString("View"));

				ps2 = db.prepareStatement("SELECT Problem FROM RoomProblems WHERE HotelID=? AND RoomNumber=?");
				ps2.setInt(1, r.getRoomHotelID());
				ps2.setInt(2, r.getRoomNumber());
				
				rs2 = ps2.executeQuery();
				while (rs2.next()) {
					r.addProblem(rs2.getString("Problem"));
				}
				ps2.close();
				rs2.close();
				
				ps2 = db.prepareStatement("SELECT Amenetie FROM RoomAmenities WHERE HotelID=? AND RoomNumber=?");
				ps2.setInt(1, r.getRoomHotelID());
				ps2.setInt(2, r.getRoomNumber());
				
				rs2 = ps2.executeQuery();
				while (rs2.next()) {
					r.addAmenetie(rs2.getString("Amenetie"));
				}
				
				out.add(r);
				
				ps2.close();
				rs2.close();
			}
		} catch(SQLException e){
            e.printStackTrace();
            return null;	 
        }finally {
        	try {
        		if (ps2 != null) ps2.close();
        		if (rs2 != null) rs2.close();
        	} catch (SQLException e) {}
        	closeDB();
        }
		
		return out;
	}

	public boolean addRoom(int hotelID, int roomNumber, String price, int capacity, String view, boolean extendable) {
		getConn();
		try{
			String sql = "INSERT INTO Room VALUES (?,?,?,?,?,?)";
        	ps = db.prepareStatement(sql);
        	ps.setInt(1, hotelID);
        	ps.setInt(2, roomNumber);
        	ps.setString(3, price);
        	ps.setInt(4, capacity);
        	ps.setString(5, view);
        	ps.setBoolean(6, extendable);
        	
			System.out.println(sql);
        	ps.execute();
        	
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}

	public boolean addRoomAmenetie(int hotelID, int roomNumber, String amenetie) {
		getConn();
		try{
			String sql = "INSERT INTO RoomAmenities VALUES (?,?,?)";
        	ps = db.prepareStatement(sql);
        	ps.setInt(1, hotelID);
        	ps.setInt(2, roomNumber);
        	ps.setString(3, amenetie);
			System.out.println(sql);
        	ps.execute();
        	
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}

	public boolean updateRoomAmenetie(int hotelID, int roomNumber, String old_amenetie, String new_amenetie) {
		getConn();
		try{
			String sql = "UPDATE RoomAmenities SET Amenetie=? WHERE HotelID=? AND RoomNumber=? AND Amenetie=?";
        	ps = db.prepareStatement(sql);
        	ps.setString(1, new_amenetie);
        	ps.setInt(2, hotelID);
        	ps.setInt(3, roomNumber);
        	ps.setString(4, old_amenetie);
			System.out.println(sql);
        	ps.execute();
        	
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}

	public boolean deleteRoomAmenetie(int hotelID, int roomNumber, String amenetie) {
		getConn();
		try{
			String sql = "DELETE FROM RoomAmenities WHERE HotelID=? AND RoomNumber=? AND Amenetie=?";
        	ps = db.prepareStatement(sql);
        	ps.setInt(1, hotelID);
        	ps.setInt(2, roomNumber);
        	ps.setString(3, amenetie);
			System.out.println(sql);
        	ps.execute();
        	
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}

	public boolean addRoomProblem(int hotelID, int roomNumber, String problem) {
		getConn();
		try{
			String sql = "INSERT INTO RoomProblems VALUES (?,?,?)";
        	ps = db.prepareStatement(sql);
        	ps.setInt(1, hotelID);
        	ps.setInt(2, roomNumber);
        	ps.setString(3, problem);
			System.out.println(sql);
        	ps.execute();
        	
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}

	public boolean updateRoomProblem(int hotelID, int roomNumber, String old_problem, String new_problem) {
		getConn();
		try{
			String sql = "UPDATE RoomProblems SET Problem=? WHERE HotelID=? AND RoomNumber=? AND Problem=?";
        	ps = db.prepareStatement(sql);
        	ps.setString(1, new_problem);
        	ps.setInt(2, hotelID);
        	ps.setInt(3, roomNumber);
        	ps.setString(4, old_problem);
			System.out.println(sql);
        	ps.execute();
        	
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}

	public boolean deleteRoomProblem(int hotelID, int roomNumber, String problem) {
		getConn();
		try{
			String sql = "DELETE FROM RoomProblems WHERE HotelID=? AND RoomNumber=? AND Problem=?";
        	ps = db.prepareStatement(sql);
        	ps.setInt(1, hotelID);
        	ps.setInt(2, roomNumber);
        	ps.setString(3, problem);
			System.out.println(sql);
        	ps.execute();
        	
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}

	public boolean updateRoom(int hotelID, int roomNumber, String price, int capacity, String view, boolean extendable) {
		getConn();
		try{
			String sql = "UPDATE Room SET Price=?, Capacity=?, View=?, Extentable=? WHERE HotelID=? AND RoomNumber=?";
        	ps = db.prepareStatement(sql);
        	ps.setString(1, price);
        	ps.setInt(2, capacity);
        	ps.setString(3, view);
        	ps.setBoolean(4, extendable);
        	ps.setInt(5, hotelID);
        	ps.setInt(6, roomNumber);
			System.out.println(sql);
        	ps.execute();
        	
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}

	public boolean deleteRoom(int hotelID, int roomNumber) {
		getConn();
		try{
			String sql = "DELETE FROM Room WHERE HotelID=? AND RoomNumber=?";
        	ps = db.prepareStatement(sql);
        	ps.setInt(1, hotelID);
        	ps.setInt(2, roomNumber);
			System.out.println(sql);
        	ps.execute();
        	
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}
	
	public ArrayList<Booking> getAllBookings(){
		getConn();
		
		ArrayList<Booking> bookings = new ArrayList<Booking>();
		
		try {
			ps = db.prepareStatement("SELECT * FROM Booking");
			rs = ps.executeQuery();
			while(rs.next()){
				int bookingId = rs.getInt("BookingID");
				Date startDate = rs.getDate("StartDate");
				int roomNumber = rs.getInt("RoomNumber");
				int hotelId= rs.getInt("HotelID");
				int customerSSN= rs.getInt("Customer");
				Date endDate = rs.getDate("EndDate");
				Booking booking = new Booking(bookingId, startDate, roomNumber, hotelId, customerSSN, endDate);
				

				
				bookings.add(booking);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
        	closeDB();
        }
					
		return bookings;
	}
	
	public ArrayList<Booking> getAllActiveBookings(){
		getConn();
		
		ArrayList<Booking> bookings = new ArrayList<Booking>();
		
		try {
			ps = db.prepareStatement("SELECT * FROM Booking WHERE NOT Exists(SELECT * from archived WHERE booking.BookingID = archived.BookingID)");
			rs = ps.executeQuery();
			while(rs.next()){
				int bookingId = rs.getInt("BookingID");
				Date startDate = rs.getDate("StartDate");
				int roomNumber = rs.getInt("RoomNumber");
				int hotelId= rs.getInt("HotelID");
				int customerSSN= rs.getInt("Customer");
				Date endDate = rs.getDate("EndDate");
				Booking booking = new Booking(bookingId, startDate, roomNumber, hotelId, customerSSN, endDate);
				

				
				bookings.add(booking);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
        	closeDB();
        }
					
		return bookings;
	}
	
	public ArrayList<Renting> getAllRentings(){
		getConn();
		
		ArrayList<Renting> rentings = new ArrayList<Renting>();
		
		try {
			ps = db.prepareStatement("SELECT * FROM Renting");
			rs = ps.executeQuery();
			while(rs.next()){
				int rentingId = rs.getInt("RentingID");
				Date startDate = rs.getDate("StartDate");
				int roomNumber = rs.getInt("RoomNumber");
				int hotelId= rs.getInt("HotelID");
				int customerSSN= rs.getInt("Customer");
				Date endDate = rs.getDate("EndDate");
				int checkedInByEmployeeSSN = rs.getInt("CheckedInByEmployeeID");
				int bookingId = rs.getInt("BookingID");
				Renting renting = new Renting(rentingId, startDate, roomNumber, hotelId, customerSSN, endDate, checkedInByEmployeeSSN, bookingId);
				
				rentings.add(renting);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
        	closeDB();
        }
					
		return rentings;
	}
	
	public ArrayList<Renting> getAllActiveRentings(){
		getConn();
		
		ArrayList<Renting> rentings = new ArrayList<Renting>();
		
		try {
			ps = db.prepareStatement("SELECT * FROM renting WHERE NOT Exists(SELECT * from archived WHERE renting.RentingID = archived.rentingID)");
			rs = ps.executeQuery();
			while(rs.next()){
				int rentingId = rs.getInt("RentingID");
				Date startDate = rs.getDate("StartDate");
				int roomNumber = rs.getInt("RoomNumber");
				int hotelId= rs.getInt("HotelID");
				int customerSSN= rs.getInt("Customer");
				Date endDate = rs.getDate("EndDate");
				int checkedInByEmployeeSSN = rs.getInt("CheckedInByEmployeeID");
				int bookingId = rs.getInt("BookingID");
				Renting renting = new Renting(rentingId, startDate, roomNumber, hotelId, customerSSN, endDate, checkedInByEmployeeSSN, bookingId);
				
				rentings.add(renting);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
        	closeDB();
        }
					
		return rentings;
	}
	
	public ArrayList<Archive> getAllArchived(){
		getConn();
		
		ArrayList<Archive> archives = new ArrayList<Archive>();
		
		try {
			ps = db.prepareStatement("SELECT * FROM Archived");
			rs = ps.executeQuery();
			while(rs.next()){
				int archiveId = rs.getInt("ArchivedID");
				int bookingId = rs.getInt("BookingID");
				int rentingId = rs.getInt("RentingID");
				Archive archive = new Archive(archiveId, bookingId, rentingId);
				
				archives.add(archive);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
        	closeDB();
        }
					
		return archives;
	}
	
	public boolean ArchiveRoom(int bookingID, int rentingID) {
		getConn();
		
		try{
        	
        	ps = db.prepareStatement("INSERT INTO Archived(BookingID, RentingID) Values("+bookingID+","+rentingID+")");
        	System.out.println(ps);
        	ps.execute();
			
            
            return true;

        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}
	
	public boolean ArchiveRoomBooking(int bookingID) {
		getConn();
		
		try{
        	
        	ps = db.prepareStatement("INSERT INTO Archived(BookingID) Values("+bookingID+")");
        	System.out.println(ps);
        	ps.execute();
			
            
            return true;

        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}
	public boolean ArchiveRoomRenting(int rentingID) {
		getConn();
		
		try{
        	
        	ps = db.prepareStatement("INSERT INTO Archived(RentingID) Values("+rentingID+")");
        	System.out.println(ps);
        	ps.execute();
			
            
            return true;

        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}
	
}
