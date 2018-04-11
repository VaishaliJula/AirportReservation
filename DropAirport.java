import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DropAirport {

public static void main(String[] args) {
		
		String dropTable = "DROP TABLE AIRPORT";
		try {
			//1 - Driver Registry
			Class.forName("com.mysql.jdbc.Driver");
			//2 - Connection to DB
			String url = "jdbc:mysql://localhost:3306/test";
			Connection conn = DriverManager.getConnection(url ,"" ,"");
			//3 Statements
			Statement stmt = conn.createStatement();
			//drop table airport
			stmt.executeUpdate(dropTable);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
