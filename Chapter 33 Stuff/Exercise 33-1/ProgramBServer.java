package seekUnderstanding;

// ProgramBServer.java

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//import loanServer.Loan;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicReference;

public class ProgramBServer extends Application implements connectionMethods{
	
	 private AtomicReference<BufferedWriter> outRef = new AtomicReference<>();

	 //The 𝀽 character is this program's seal of security.
     
     private String splitRegex = "[𝀽]";
     private String doubleRegex = "-?\\d+(\\.\\d+)?"; 
	 private String intRegex = "^\\d+$";
     
     private volatile boolean running = true;
     private static String usage; //Write to server
     private static String usageConsole; //Save on server;
     private String clientAddress;
     
     // Text area for displaying contents 
     private static TextArea ta = new TextArea();

     @Override
     public void start(Stage primaryStage) {

          ta.setWrapText(true);

          // Create a scene and place it in the stage
          Scene scene = new Scene(new ScrollPane(ta), 400, 200);
          primaryStage.setTitle("Exercise31_01Server"); // Set the stage title
          primaryStage.setScene(scene); // Place the scene in the stage
          primaryStage.show(); // Display the stage

          // Start server thread
          Thread t = new Thread(this::serve, "tcp-server-thread");
          t.setDaemon(true);
          t.start();

          primaryStage.setOnCloseRequest(e -> running = false);
          
          // Connect in a background thread
          new Thread(() -> connectionMethods.connect(5051, outRef), "tcp-server-connect-thread").start();

     }

     private void serve() {

          try (ServerSocket server = new ServerSocket(5050)) {

               ui(() -> ta.setText("Listening on 5050…"));

               while (running) {
            	   
                    try (
                         Socket sock = server.accept();
                         BufferedReader in = new BufferedReader(
                              new InputStreamReader(sock.getInputStream(), StandardCharsets.UTF_8))
                    ) {
                    	
                    	 clientAddress = sock.getRemoteSocketAddress().toString();
                    	 
                    	 String dateAndTime = properlyFormattedDate();
                    	 
                    	 usage = "Client connected at: " + clientAddress +
                    			 " at " + dateAndTime;

                         addUsage();

                         String line;
                         
                         while ((line = in.readLine()) != null && running) {
                              // Very naive JSON parse: extract value after "value":
                              String value = connectionMethods.extractValue(line); // see helper below
                              
                    		  String service; //Send to client
                    		  
                              if (value != null) {
                            	  
                            	   ui(() -> ta.setText(value));
                            	  
                            	   boolean complete = true;

                            	   String[] info = value.split(splitRegex);
                            	   
                            	   for (String s : info) {
                            		   
                            		   if (s == "") {
                            			   
                            			   complete = false;
                            			   
                            		   } //if (s == "")
                            		   
                            	   } //for (String s : info)
                            	   
                            	   if (complete) {
                            	   
                            	   if (info.length != 3) {
                            		   
                            		   usage = "Make sure to fill ALL boxes.";
                            		   addUsage();
                            		   
                            	   } else {
                            		   
                            		  String interestRate = info[0];
                            		  String years = info[1];
                            		  String loanAmount = info[2];
                            		   
                                 	  if (interestRate.matches(doubleRegex) && years.matches(doubleRegex) && loanAmount.matches(doubleRegex)) {
                                		  
                                		  if (years.matches(intRegex)) {
                                	  
                                			  double InterestRate = Double.valueOf(interestRate);
                                			  int Years = Integer.valueOf(years);
                                			  double LoanAmount = Double.valueOf(loanAmount);
                                	  
                                			  Loan thisLoan = new Loan(InterestRate, Years, LoanAmount);
                                			  
                                			  usage = "\nSent to client: \n"
                                			  		  + "Annual Interest Rate: " + thisLoan.getAnnualInterestRate() + "% \n"
                                					  + "Number of Years: " + thisLoan.getNumberOfYears() + " \n"
                                					  + "Loan Amount: $" + thisLoan.getLoanAmount() + " \n"
                                					  + "Monthly Payment: $" + thisLoan.getMonthlyPayment() + " \n"
                                					  + "Total Payment: $" + thisLoan.getTotalPayment();
                                			  
                                			  addUsage();
                                	  
                                			  service = "Annual Interest Rate: " + thisLoan.getAnnualInterestRate() + "%𝀽"
                                					  + "Number of Years: " + thisLoan.getNumberOfYears() + "𝀽"
                                					  + "Loan Amount: $" + thisLoan.getLoanAmount() + "𝀽"
                                					  + "Monthly Payment: $" + thisLoan.getMonthlyPayment() + "𝀽"
                                					  + "Total Payment: $" + thisLoan.getTotalPayment();
                                			  
                                			  connectionMethods.sendJson(outRef.get(), service);
                                		  
                                		 } else {
                                			 
                                			 usage = "\nSent to client: \n"
                                			 		+ "'Number Of Years' must be a whole number.";
                                			 service = "'Number Of Years' must be a whole number. 𝀽 𝀽 𝀽 𝀽 ";
                                			 
                                			 addUsage();
                                			 connectionMethods.sendJson(outRef.get(), service);
                                			  
                                		 } //if (years.matches(intRegex)) {...} else
                                	  
                                	  } else {
                                		  
                                		  usage = "\nSent to client: \n"
                                		  		+ "Inputs must be numeric.";
                                		  service = "Inputs must be numeric. 𝀽 𝀽 𝀽 𝀽 ";
                                		  
                                		  addUsage();
                                		  connectionMethods.sendJson(outRef.get(), service);
                                		  
                                	  } //if (interestRate.matches(regex) && loanAmount.matches(regex)) {...} else
                            		   
                            	   } //if (info.length != 3) {...} else
                            	   
                            	   } else {
                            		   
                            		   usage = "\nSent to client: \n"
                            		   		+ "Make sure to fill ALL boxes.";
                            		   service = "Make sure to fill ALL boxes. 𝀽 𝀽 𝀽 𝀽 ";
                            		   
                            		   addUsage();
                            		   connectionMethods.sendJson(outRef.get(), service);
                            		   
                            	   } //if (complete) {...} else
                                   
                              } //if (value != null)
                              
                         } //while ((line = in.readLine()) != null && running)

                         usage = "\nClient at " + clientAddress + " disconnected";
                         addUsage();

                    } catch (IOException ignored) {
                    	
                         usage = "\nConnection error; still listening...";
                         addUsage();
                         
                    } //try(...) {...} catch (IOException ignored)
                    
               } //while (running)

          } catch (IOException ex) {
        	  
               usage = "\nFailed to bind port 5050: " + ex.getMessage();
               addUsage();
               
          } //try (ServerSocket server = new ServerSocket(5050)) {...} catch (IOException ex)

     } //private void serve()

     private static void ui(Runnable r) {

          Platform.runLater(r);

     } //private static void ui(Runnable r)
     
     private static void addUsage() {
    	 
    	 if (usageConsole == null) {
    		 
    		 usageConsole = usage;
    		 
    	 } else {
    		 
    		 usageConsole += usage; 
    		 
    	 } //if (console == null) {...} else
    	 
    	 ui(() -> ta.setText(usageConsole));
    	 
     } //private static String addUsage(String console, String usage)
     
     private static String properlyFormattedDate() {
    	 
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

} //public class ProgramBServer extends Application
