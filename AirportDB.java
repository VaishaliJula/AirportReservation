import java.sql.*;

public class AirportDB{


	Statement stmt;
	Connection conn;
	String createTableAirport = "CREATE TABLE AIRPORT " +
		    "(AirportID INTEGER, AirportName VARCHAR(32), AirportRegionCode INTEGER)";
	String query = "Select * from AIRPORT ";
	
	
	AirportDB(){
		try {
			//1 - Driver Registry
			Class.forName("com.mysql.jdbc.Driver");
			//2 - Connection to DB
			String url = "jdbc:mysql://localhost:3306/test";
			conn = DriverManager.getConnection(url ,"" ,"");
			//3 Statements
			stmt = conn.createStatement();
			//creating table airport
			stmt.executeUpdate(createTableAirport);
			
			//adding fields/ data to airport table
			stmt.executeUpdate("INSERT INTO AIRPORT " + "VALUES (1221, 'RG', 6)");
			stmt.executeUpdate("INSERT INTO AIRPORT " + "VALUES (3443, 'sa', 8)");
			stmt.executeUpdate("INSERT INTO AIRPORT " + "VALUES (5665, 'DelhiI', 12)");

			
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				int AiD = rs.getInt("AirportID");
				String AName = rs.getString("AirportName");
				System.out.println(AiD + "   " + AName);
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	

public static void main(String[] args) {
		
		new AirportDB();
		
	}

}
