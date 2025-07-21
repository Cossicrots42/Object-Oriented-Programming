package Exercise21_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Exercise21_11 extends Application {
	
     private Button btFindRanking = new Button("Find Ranking");
     private ComboBox<Integer> cboYear = new ComboBox<>();
     private ComboBox<String> cboGender = new ComboBox<>();
     private TextField tfName = new TextField();
     private Label lblResult = new Label();

     private String[] urlString = {
          "https://liveexample.pearsoncmg.com/data/babynamesranking2001.txt",
          "https://liveexample.pearsoncmg.com/data/babynamesranking2002.txt",
          "https://liveexample.pearsoncmg.com/data/babynamesranking2003.txt",
          "https://liveexample.pearsoncmg.com/data/babynamesranking2004.txt",
          "https://liveexample.pearsoncmg.com/data/babynamesranking2005.txt",
          "https://liveexample.pearsoncmg.com/data/babynamesranking2006.txt",
          "https://liveexample.pearsoncmg.com/data/babynamesranking2007.txt",
          "https://liveexample.pearsoncmg.com/data/babynamesranking2008.txt",
          "https://liveexample.pearsoncmg.com/data/babynamesranking2009.txt",
          "https://liveexample.pearsoncmg.com/data/babynamesranking2010.txt"
     }; //private String[] urlString =
     
     private HashMap[] boyNames = new HashMap[10];
     private HashMap[] girlNames = new HashMap[10];     

     @Override
     public void start(Stage primaryStage) {
    	 
    	 EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
    		 
             public void handle(ActionEvent e) {
            	 
            	 if (cboGender.getValue() != "--CHOOSE--") {
            		 
            		 if (cboGender.getValue() == "Male") {
            			 //I might just be a genius. Like, look at this!
            			 String name = tfName.getText();
            			 int year = cboYear.getValue();
            			 
            			 if (boyNames[year - 2001].containsKey(name)) {
            			 
            				 lblResult.setText("Boy name " + tfName.getText() + " is ranked #" 
            				 + boyNames[year - 2001].get(name) + " in year "+ year);
            			 
            			 } else {
            				 
            				 lblResult.setText(name + " is not in the top 1,000 boy names for " + year);
            				 
            			 }
            			 
            		 } //if (cboGender.getValue() == "Male")
            		 
            		 if (cboGender.getValue() == "Female") {
            			 //I might just be a genius. Like, look at this!
            			 String name = tfName.getText();
            			 int year = cboYear.getValue();
            			 
            			 if (girlNames[year - 2001].containsKey(name)) {
            			 
            				 lblResult.setText("Girl name " + tfName.getText() + " is ranked #" 
            				 + girlNames[year - 2001].get(name) + " in year "+ year);
            			 
            			 }else {
            				 
            				 lblResult.setText(name + " is not in the top 1,000 girl names for " + year);
            				 
            			 }
            			 
            		 } //if (cboGender.getValue() == "Female")
            		 
            	 } //if (cboGender.getValue() != "--CHOOSE--")
            	 
             } //public void handle(ActionEvent e)
             
         }; //EventHandler<ActionEvent> event = new EventHandler<ActionEvent>()
    	 
    	 btFindRanking.setOnAction(event);
    	 
    	 for (int j = 0; j < urlString.length; j++) {
    	 
    		 boyNames[j] = new HashMap<String, Integer>();
    		 girlNames[j] = new HashMap<String, Integer>();
         
    	 } //for (int j = 0; j < urlString.length; j++) 
    	 
    	 try {
    			  
    	     for (int i = 0; i < urlString.length; i++) {
        	  
    			  URL url = new URL(urlString[i]);
    			  HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    			  conn.setRequestMethod("GET");
    			  conn.setConnectTimeout(5_000);
    			  conn.setReadTimeout(5_000);

    			  try (BufferedReader reader = new BufferedReader(
                         new InputStreamReader(conn.getInputStream()))) {

    				  	String line;
    				  	                                        
                    	while (((line = reader.readLine()) != null)) {

                    		String[] stuff = line.split("[\\s+\\p{P}]");
                    		
                    		boyNames[i].put(stuff[2], Integer.valueOf(stuff[0]));
                    		girlNames[i].put(stuff[5], Integer.valueOf(stuff[0]));
                    		                    	
                    	} //while ((line = reader.readLine()) != null)

    			  } //try (BufferedReader reader = new BufferedReader(
                 //new InputStreamReader(conn.getInputStream())))

    		   conn.disconnect();
    			  
    	       }//for (int i = 0; i < urlString.length; i++)
    			  
    	   } catch (IOException e) {

    		   System.err.println("Failed to read from URL: " + e.getMessage());
    		   e.printStackTrace();

    	   } //catch (IOException e)
          
          GridPane gridPane = new GridPane();

          gridPane.add(new Label("Select a year:"), 0, 0);
          gridPane.add(new Label("Boy or girl?"), 0, 1);
          gridPane.add(new Label("Enter a name:"), 0, 2);
          gridPane.add(cboYear, 1, 0);
          gridPane.add(cboGender, 1, 1);
          gridPane.add(tfName, 1, 2);
          gridPane.add(btFindRanking, 1, 3);
          gridPane.setAlignment(Pos.CENTER);
          gridPane.setHgap(5);
          gridPane.setVgap(5);

          BorderPane borderPane = new BorderPane();

          borderPane.setCenter(gridPane);
          borderPane.setBottom(lblResult);
          BorderPane.setAlignment(lblResult, Pos.CENTER);

          Scene scene = new Scene(borderPane, 370, 160);

          primaryStage.setTitle("Exercise21_11");
          primaryStage.setScene(scene);
          primaryStage.show();

          for (int year = 2001; year <= 2010; year++) {

               cboYear.getItems().add(year);

          } //for (int year = 2001; year <= 2010; year++)

          cboYear.setValue(2001);

          cboGender.getItems().addAll("Male", "Female", "--CHOOSE--");
          cboGender.setValue("--CHOOSE--");

     } //public void start(Stage primaryStage)

     /**
      * The main method is only needed for the IDE with limited
      * JavaFX support. Not needed for running from the command line.
      */
     public static void main(String[] args) {

          launch(args);

     } //public static void main(String[] args)

} //public class Exercise21_11 extends Application
