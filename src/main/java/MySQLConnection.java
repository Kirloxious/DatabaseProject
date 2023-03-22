import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class MySQLConnection {

	Connection db = null;
	PreparedStatement ps = null;
    ResultSet rs = null;
    Statement st = null;
    String sql;
	
	public MySQLConnection() {
	}
	
	public void getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			db = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/e-hotels", "root", "rootlogin123");
			System.out.println("Connected to MySQL server");
			
		}catch (SQLException e){
            System.out.println("Error in connecting to MySQL server");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			System.out.println("SQL Driver not Found.");
			e.printStackTrace();
		}
	}
	
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
	
	public static void main(String[] args) {
	}
}
