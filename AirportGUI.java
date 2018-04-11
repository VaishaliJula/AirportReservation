import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

//import com.mysql.jdbc.PreparedStatement;

public class AirportGUI extends Applet implements ActionListener {
    //GUI data members
	TextField textFieldFid,ticketPrice,newFlightTimings, seats;
	Button search, modify, newFlight, save, cancel;
	Choice originCh, destCh, avilableFlightsCh;
	Label Origin,Destination,AvailableFlights;
	String[] originIdArray;
	String selectedSrc,selectedDest;
	int srcZipCode, destinationZipCode, BasePrice = 200,NewPrice;
	
	//DB data members
	   Connection con;
	   Statement stmt;
	   PreparedStatement ps2,ps1;
	   String query = "SELECT * FROM AIRPORT ";
	   
	public  AirportGUI() {
		//Labels and text boxes
		Label Header = new Label("FLIGHT RESERVATION");
		Header.setText("WELCOME TO THE FLIGHT RESERVATION CENTER");
		Header.setBounds(500,500,100,100);
		
		Label FlightID = new Label("FLIGHT ID: ");
		textFieldFid = new TextField("FlightID");
		
		Label TicketP = new Label("Ticket Price");
		ticketPrice = new TextField("ticketPrice");
		
		Label FlightTiming = new Label("NewFlightTimings: ");
		newFlightTimings = new TextField("FlightTiming");
		
		Label SeatsAv = new Label("Seats: ");
		seats = new TextField("Seats Available");

        //Buttons
		search = new Button("SEARCH");
		search.addActionListener(this);
		modify = new Button("MODIFY");
		modify.addActionListener(this);
		newFlight = new Button("NEW Flight");
		newFlight.addActionListener(this);
		save = new Button("SAVE");
		save.addActionListener(this);
		cancel = new Button("CANCEL");
		cancel.addActionListener(this);
		
		//Choices
		originCh = new Choice();
		destCh = new Choice();
		avilableFlightsCh = new Choice();
		
		add(Header);
		add(FlightID);
		add(textFieldFid);
		add(TicketP);
		add(ticketPrice);
		add(FlightTiming);
		add(newFlightTimings);
		add(SeatsAv);
		add(seats);
		
		add(search);
		add(modify);
		add(newFlight);
		add(save);
		add(cancel);
		
		Origin = new Label("Origin:");
		add(Origin);
		add(originCh);
		Destination = new Label("Destination:");
		add(Destination);
		add(destCh);
		AvailableFlights = new Label("AvailableFlights:");
		add(AvailableFlights);
		add(avilableFlightsCh);
		
		String url   = "jdbc:mysql://localhost:3306/test";

        try {
                // Load the driver
                Class.forName ("com.mysql.jdbc.Driver");

                // Attempt to connect to a driver.
                 con = DriverManager.getConnection (
                                url, "", "" );

                // Create a Statement object so we can submit
                // SQL statements to the driver
                stmt = con.createStatement ();

	 }catch (SQLException ex) {  }
	 catch (Exception ex) {  }

        this.loadChoiceOrigin();
        this.loadChoiceDestination();
	}

	private void loadChoiceOrigin() {
		try {
			   int i = 0;
			   originIdArray = new String[100];
				ResultSet rs = stmt.executeQuery ("SELECT AirportID FROM AIRPORT");

				 while ( rs.next() ){
					 String s = rs.getString(1);
					 originCh.addItem(s);
					 originIdArray[i++] = s;
					 }


					 } catch (SQLException ex){                    }
		
	}
	
	
	private void loadChoiceDestination() {
		try {
			   int i = 0;
			   originIdArray = new String[100];
				ResultSet rs = stmt.executeQuery ("SELECT AirportID FROM AIRPORT");

				 while ( rs.next() ){
					 String s = rs.getString(1);
					 destCh.addItem(s);
					// originIdArray[i++] = s;
					 }


					 } catch (SQLException ex){                    }
		
	}
	
	
	private void loadChoiceAvailableFlights() {
		try {
			   int i = 0;
			   //originIdArray = new String[100];
				ResultSet rs = stmt.executeQuery ("SELECT NumberSeatsAvailable,DepartureTime FROM SCHEDULE");

				 while ( rs.next() ){
					 String s = rs.getString(1);
					 avilableFlightsCh.addItem(s);
					 //originIdArray[i++] = s;
					 }
				 selectedSrc = originCh.getSelectedItem();
				 selectedDest = destCh.getSelectedItem();
				 
				 //fetching src zip
		         String fetchSrcZip = "SELECT AirportRegionCode FROM AIRPORT " + "WHERE AirportID = '" +selectedSrc+ "'";
				 ps1 = con.prepareStatement(fetchSrcZip);
				 ResultSet srcZip = stmt.executeQuery(fetchSrcZip);
		         while (srcZip.next()) {
						srcZipCode = srcZip.getInt("AirportRegionCode");
						System.out.println("Source zip "+srcZipCode);
					}
		        
				 //fetching destination zip
		         String fetchDestZip = "SELECT AirportRegionCode FROM AIRPORT " + "WHERE AirportID = '" +selectedDest+ "'";
				 ps2 = con.prepareStatement(fetchDestZip);

		         ResultSet destZip = stmt.executeQuery(fetchDestZip);
		         while (destZip.next()) {
						destinationZipCode = destZip.getInt("AirportRegionCode");
						System.out.println("Destination zip "+destinationZipCode);
					}
		         
		         
				 if(srcZipCode == destinationZipCode) {
					 System.out.println(BasePrice);
					 ticketPrice.setText("$"+BasePrice);
				 }
				 else {
					 int diffZip = (srcZipCode - destinationZipCode);
					 NewPrice = BasePrice+(diffZip*100);
					 System.out.println(NewPrice);
					 ticketPrice.setText("$"+NewPrice);
					 
				 }


         } catch (SQLException ex){                    }
		
	}

	public void actionPerformed(ActionEvent e) {

		try {
			if(e.getSource() == search) {
				loadChoiceAvailableFlights();
			}else if(e.getSource() == cancel) {
				
				ticketPrice.setText("");
			}else if(e.getSource() == modify) {
				System.out.println(newFlightTimings.getText());
				System.out.println(seats.getText());
				System.out.println(textFieldFid.getText());
			}else if(e.getSource() == save) {
				
			}
			else {System.out.println("do something");}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

public static void main(String[] args) {
		
		new AirportGUI();
		}
}
