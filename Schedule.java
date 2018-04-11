import java.sql.*;

public class Schedule {

	Statement stmt;
	Connection conn;
	String createScheduleTable = "CREATE TABLE SCHEDULE " +
		    "(FlightNumber VARCHAR(100), "
		    + "OriginID INTEGER,"
		    + " DestinationID INTEGER,"
	        + "NumberSeatsAvailable INTEGER, DepartureTime DATE)";
	String query = "Select * from SCHEDULE ";
	
	Schedule(){
		try {
			//1 - Driver Registry
			Class.forName("com.mysql.jdbc.Driver");
			//2 - Connection to DB
			String url = "jdbc:mysql://localhost:3306/test";
			conn = DriverManager.getConnection(url ,"" ,"");
			//3 Statements
			stmt = conn.createStatement();
			//creating table schedule
			stmt.executeUpdate(createScheduleTable);
			
			//adding fields/ data to schedule table
			stmt.executeUpdate("INSERT INTO SCHEDULE " + "VALUES ('34RGI', 3443, 5665, 19, '2018-01-26')");
			stmt.executeUpdate("INSERT INTO SCHEDULE " + "VALUES ('12RGI', 1221, 5665, 21, '2018-01-21')");
			
			
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				int FlightNo = rs.getInt("FlightNumber");
				String NoSeats = rs.getString("NumberSeatsAvailable");
				System.out.println(FlightNo + "   " + NoSeats);
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	

public static void main(String[] args) {
		
		new Schedule();
		
	}


}
