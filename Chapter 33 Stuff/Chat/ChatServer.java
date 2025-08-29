package Chat;

// ProgramBServer.java

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;
import java.util.Date;

public class ChatServer extends Application implements SharedData {
 
	 static String message;

	 static Thread[] CTs = new Thread[connections.length];
	 
	 private static String clientAddress;

     private static String chatConsole; // Save on server

     // Text area for displaying contents 
     private static TextArea ta = new TextArea();

     @Override
     public void start(Stage primaryStage) {

          ta.setWrapText(true);

          // Create a scene and place it in the stage
          Scene scene = new Scene(new ScrollPane(ta), 400, 200);
          primaryStage.setTitle("Chat Server"); // Set the stage title
          primaryStage.setScene(scene); // Place the scene in the stage
          primaryStage.show(); // Display the stage
          
          for (int i = 0; i < connections.length; i++) {
        	  
        	  CTs[i] = connectionThread(i);
        	  CTs[i].start();
        	  
          } //for (int i = 0; i < connections.length; i++)

          
     } //public void start(Stage primaryStage)
     
     static Thread connectionThread(int index) {
    	 
    	 return (new Thread(() -> {

             try {
                  // Create a server socket
                  ServerSocket serverSocket = new ServerSocket(port + index);
                  addChat("Listening on port: " + (port + index));

                  // Listen for a connection request
                  Socket socket = serverSocket.accept();
                  clientAddress = socket.getRemoteSocketAddress().toString();
                  
                  addChat("Client connected at " + clientAddress + " at " + properlyFormattedDate());

                  connections[index] = new DirectStringTransmitter(socket);

                  while (true) {

                  	 message = connections[index].receive();

                  	 addChat("Recieved: " + message);
                                              	 
            		 sendEveryone(message);

                  } //while (true)

             } catch (IOException ex) {
          	   
                  ex.printStackTrace();
                  
             } //try {...} catch (IOException ex)

        }));
    	    	 
     } //static Thread connectionThread()
     
     static void sendEveryone(String text) {
    	 
    	 for (DirectStringTransmitter conn : connections) {
    		 
    		 conn.send(text);
    		 
    	 } //for (DirectStringTransmitter conn : connections)
    	 
     } //private static void sendEveryone(String text)

     static void addChat(String chat) {

          if (chatConsole == null) {
        	  
               chatConsole = chat;
               
          } else {
        	  
               chatConsole += "\n" + chat;
               
          } //if (chatConsole == null) {...} else
          
          helperUi(() -> ta.setText(chatConsole));

     } //private static void addchat()
     
     static void helperUi(Runnable r) {

         Platform.runLater(r);

    } //private static void ui(Runnable r)
     
     static String properlyFormattedDate() {
    	 
    	 String result;
    	 
    	 String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    	 String[] monthsOfYear = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    	 
    	 Date theDate = new Date();
    	 
    	 Instant timestamp = Instant.now();
    	 
    	 String time = timestamp.toString().substring(11, 18);
    	 
    	 int day = theDate.getDay();
    	 int month = theDate.getMonth();
    	 
    	 result = daysOfWeek[theDate.getDay()] + " " + monthsOfYear[theDate.getMonth()] + " " + 
    	 theDate.getDate() + " " + time + " UTC " + (theDate.getYear() + 1900);
    	 
    	 return result;
    	 
     } //private static String properlyFormattedDate()

     public static void main(String[] args) {

          launch(args);

     } //public static void main(String[] args)

} //public class ChatServer extends Application implements connectionMethods
