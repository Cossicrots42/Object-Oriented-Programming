package Chat;

import javafx.scene.paint.Color;

public interface SharedData {
	
	// Functional Data for Clients
	static int port = 5080;
	static String regex = "[𝀽]";
	
	//TO ADD ANOTHER CLIENT, INCREASE THE LENGTH OF ALL THESE ARRAYS BY ONE, AND THEN CREATE A COPY OF ANY 
	//CLIENT CLASS AND ENSURE ITS 'int client' FIELD IS UNIQUE. IT'S THAT EASY.
	
	// Clients Data
	static String[] chatNames = {"Janna", "Banana", "Hannah"};
	static Color[] bubbleColors = {Color.rgb(29, 101, 219), Color.rgb(22, 115, 18), Color.rgb(123, 50, 191)};
	
	// Server connection objects
	static DirectStringTransmitter[] connections = {
			new DirectStringTransmitter(), 
			new DirectStringTransmitter(),
			new DirectStringTransmitter()
		};

} //public interface SharedData
