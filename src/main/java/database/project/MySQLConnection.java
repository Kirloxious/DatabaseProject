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
	public ArrayList<Room> getAllAvailableRooms(){
		getConn();
		
		ArrayList<Room> Rooms = new ArrayList<Room>();
		
		try {//TODO make use of view?
			ps = db.prepareStatement("SELECT room.HotelID, room.RoomNumber, room.Price, room.Capacity, room.View, room.Extentable, roomamenities.Amenetie, roomproblems.Problem FROM ((room \r\n"
					+ "INNER JOIN roomamenities ON (room.HotelID = roomamenities.HotelID AND room.RoomNumber = roomamenities.RoomNumber))\r\n"
					+ "INNER JOIN roomproblems ON (room.HotelID = roomproblems.HotelID AND room.RoomNumber = roomproblems.RoomNumber))\r\n"
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
				String roomAmenitie = rs.getString("Amenetie");
				String roomProblem = rs.getString("Problem");
				Room room = new Room(roomNumber, roomHotelID, roomPrice, roomCapacity, roomView, extentable, roomProblem, roomAmenitie);
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
		
		try{
        	
        	ps = db.prepareStatement("INSERT INTO Renting VALUES ('"+startDate+"', "+roomNumber+","+roomHotelID+", "+custSSN+", "+endDate+", "+employeeSSN+", "+BookingID+")");
			System.out.println(ps);
        	rs = ps.executeQuery();
			
            
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
		
		try{
        	
        	ps = db.prepareStatement("INSERT INTO Renting VALUES ('"+startDate+"', "+roomNumber+","+roomHotelID+", "+custSSN+", "+endDate+", "+employeeSSN+")");
			System.out.println(ps);
        	rs = ps.executeQuery();
			
            
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;	 
        }finally {
        	closeDB();
        }
	}
	
	public ArrayList<Integer> getHotels() {
		//TODO implement return list of all hotelIds
		ArrayList<Integer> out = new ArrayList<Integer>();
		out.add(1);
		out.add(2);
		out.add(5);
		return out;
	} 

	public boolean addCustomerAddress(int ssn, String address) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteCustomerAddress(int ssn, String address) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateCustomerAddress(int ssn, String oldAddress, String newAddress) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateCustomer(int ssn, String first_name, String middle_name, String last_name, String date) {
		//update customer ssn with new data
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteCustomer(int ssn) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addEmployeeAddress(int ssn, String address) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateEmployeeAddress(int ssn, String old_address, String new_address) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteEmployeeAddress(int ssn, String old_address) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateEmployee(int ssn, String hotel_id, String first_name, String middle_name,
			String last_name, String date) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteEmployee(int ssn) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateEmployeerole(int ssn, String old_role, String new_role) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteEmployeeRole(int ssn, String old_role) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addEmployeeRole(int parseInt, String role) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
