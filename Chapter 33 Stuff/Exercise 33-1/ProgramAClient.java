package seekUnderstanding;

// ProgramAClient.java

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

public class ProgramAClient extends Application implements connectionMethods{

     private AtomicReference<BufferedWriter> outRef = new AtomicReference<>();
     
     private String splitRegex = "[𝀽]";
     
     // Text fields for receiving input
     private TextField tfAnnualInterestRate = new TextField();
     private TextField tfNumOfYears = new TextField();
     private TextField tfLoanAmount = new TextField();
     
     private Button btSubmit = new Button("Submit");
     
     private volatile boolean running = true;

     // Text area to display contents
     private TextArea ta = new TextArea();

     @Override
     public void start(Stage primaryStage) {

    	 ta.setWrapText(true);

         GridPane gridPane = new GridPane();
         gridPane.add(new Label("Annual Interest Rate"), 0, 0);
         gridPane.add(new Label("Number Of Years"), 0, 1);
         gridPane.add(new Label("Loan Amount"), 0, 2);
         gridPane.add(tfAnnualInterestRate, 1, 0);
         gridPane.add(tfNumOfYears, 1, 1);
         gridPane.add(tfLoanAmount, 1, 2);
         gridPane.add(btSubmit, 2, 1);

         tfAnnualInterestRate.setAlignment(Pos.BASELINE_RIGHT);
         tfNumOfYears.setAlignment(Pos.BASELINE_RIGHT);
         tfLoanAmount.setAlignment(Pos.BASELINE_RIGHT);

         tfAnnualInterestRate.setPrefColumnCount(5);
         tfNumOfYears.setPrefColumnCount(5);
         tfLoanAmount.setPrefColumnCount(5);

         BorderPane pane = new BorderPane();
         pane.setCenter(new ScrollPane(ta));
         pane.setTop(gridPane);

         // Create a scene and place it in the stage
         Scene scene = new Scene(pane, 400, 250);
         primaryStage.setTitle("Exercise31_01Client"); // Set the stage title
         primaryStage.setScene(scene); // Place the scene in the stage
         primaryStage.show(); // Display the stage

         // Start client thread
         Thread t = new Thread(this::recieve, "tcp-client-thread");
         t.setDaemon(true);
         t.start();

         primaryStage.setOnCloseRequest(e -> running = false);
         
          // Connect in a background thread
         new Thread(() -> connectionMethods.connect(5050, outRef), "tcp-client-connect-thread").start();
         
         // Send text on click 'Submit'
         btSubmit.setOnAction(event -> {
        	 
        	String information = tfAnnualInterestRate.getText() + "𝀽" +
        			tfNumOfYears.getText() + "𝀽" +
        			tfLoanAmount.getText();
        	  
        	connectionMethods.sendJson(outRef.get(), information);
         
        });

        primaryStage.setOnCloseRequest(e -> connectionMethods.closeQuietly(outRef.get()));

     } //public void start(Stage primaryStage)
     
     private void recieve() {

         try (ServerSocket server = new ServerSocket(5051)) {

              ui(() -> ta.setText("Listening..."));

              while (running) {
           	   
                   try (
                        Socket sock = server.accept();
                        BufferedReader in = new BufferedReader(
                             new InputStreamReader(sock.getInputStream(), StandardCharsets.UTF_8))
                   ) {

                        ui(() -> ta.setText("Server connected!"));

                        String line;
                        
                        while ((line = in.readLine()) != null && running) {
                        	
                        	String value = connectionMethods.extractValue(line);
                        	
                        	String[] info = value.split(splitRegex);
                        	
                        	if (info.length != 5) {
                        		
                        		ui(() -> ta.setText("ERROR: Either deffective server or foreign connection!"));
                        		
                        	} else {
                        		
                        		String information = info[0] + "\n" + info[1] + "\n" + info[2] + "\n" + info[3] + "\n" + info[4];
                        		
                        		ui(() -> ta.setText(information));
                        		
                        	} //if (info.length != 5) {...} else
                             
                        } //while ((line = in.readLine()) != null && running)

                        ui(() -> ta.setText("Server disconnected."));

                   } catch (IOException ignored) {
                   	
                        ui(() -> ta.setText("Connection error; still listening…"));
                        
                   } //try(...) {...} catch (IOException ignored)
                   
              } //while (running)

         } catch (IOException ex) {
       	  
              ui(() -> ta.setText("Something went wrong..."));
              
         } //try (ServerSocket server = new ServerSocket(5050)) {...} catch (IOException ex)

    } //private void serve()
     
     private static void ui(Runnable r) {

         Platform.runLater(r);

    } //private static void ui(Runnable r)

     public static void main(String[] args) {

          launch(args);

     } //public static void main(String[] args)

} //public class ProgramAClient extends Application
