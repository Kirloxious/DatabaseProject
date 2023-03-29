package database.project;

import java.sql.Connection;
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
	 * @param params String[SSN, FirstName, MiddleName, LastName, Address]
	 * */
	public boolean insertCustomer(String[] params) {
		getConn();
		
		 try{
	        	st = db.createStatement();
	        	//insert customer
	        	sql = "insert into Customer values("+params[0]+",'"+params[1]+"','"+params[2]+"','"+params[3]+"','"+params[4]+"')";
	        	st.executeUpdate(sql);
	        	System.out.print(sql);
	        	
	        	//insert customer's address
	        	sql = "insert into CustomerAddress values("+params[0]+",'"+params[5]+"')";
	        	System.out.print(sql);
	            st.executeUpdate(sql);
	            
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
	
	/**
	 * Updates the information of a Customer at the given columnName with newInfo for the customer with custSSN.
	 * */
	public boolean updateCustomerInfo(String columnName, String newInfo, Integer custSSN) {
		getConn();
		
		try{
            ps = db.prepareStatement("UPDATE Customer SET "+columnName+"=? WHERE SSN=?");
            ps.setString(1, newInfo);
            ps.setInt(2, custSSN);
            System.out.println(ps);
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }finally {
        	closeDB();
        }
		
	}
	
	
	/**
	 * Inserts new employee into the database
	 * @param params String[SSN, HotelID, FirstName, MiddleName, LastName, RoleTitle, Address]
	 * */
	public boolean insertEmployee(String[] params) {
		getConn();
		
		 try{
	        	st = db.createStatement();
	        	//insert employee
	        	sql = "insert into Employee values("+params[0]+","+params[1]+",'"+params[2]+"','"+params[3]+"','"+params[4]+"')";
	        	st.executeUpdate(sql);
	        	System.out.println(sql);
	        	
	        	//insert emlpoyee's role
	        	sql = "insert into Role values("+params[0]+",'"+params[5]+"')";
	        	System.out.println(sql);
	            st.executeUpdate(sql);
	            
	            
	            //insert emlpoyee's Address
	        	sql = "insert into EmployeeAddress values("+params[0]+",'"+params[6]+"')";
	        	System.out.println(sql);
	            st.executeUpdate(sql);
	            
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
	
	/**
	 * Updates the information of an Employee at the given columnName with newInfo for the customer with empSSN.
	 * */
	public boolean updateEmployeeInfo(String columnName, String newInfo, Integer empSSN) {
		getConn();
		
		try{
            ps = db.prepareStatement("UPDATE Employee SET "+columnName+"=? WHERE SSN=?");
            ps.setString(1, newInfo);
            ps.setInt(2, empSSN);
            System.out.println(ps);
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }finally {
        	closeDB();
        }
		
	}
	
	
	/**
	 * Returns all the rooms which do not have a booking or renting currently.
	 * */
	public ArrayList<Room> getAllAvailableRooms(){
		getConn();
		
		ArrayList<Room> Rooms = new ArrayList<Room>();
		
		try {
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
        	
        	ps = db.prepareStatement("INSERT INTO Booking VALUES ('"+startDate+"', "+roomNumber+","+roomHotelID+", "+custSSN+", "+endDate+")");
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
	
	
	
	
	
	public static void main(String[] args) {
	
		MySQLConnection con = new MySQLConnection();
		
		
	}
	
	
}
