package Chat;

//ProgramAClient.java

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class ChatClient2 extends Application implements SharedData {
	
  // Talk with the server
  static DirectStringTransmitter connection;
  static String message;
  static int client = 1;
  
  // Handle messages
  public static int amountOfMessages = 0;

  // Text area to display contents
  private static TextArea ta = new TextArea();
  private static Pane theChatContainer = new Pane();
  private static ScrollPane theChat = new ScrollPane();
  private static Label status = new Label();
  
  // Additional Node
  private static Rectangle background = new Rectangle(400, 200);
  
  private volatile boolean running = true;

  @Override
  public void start(Stage primaryStage) {

 	  ta.setWrapText(true);
 	  
 	  theChatContainer.setStyle("-fx-background-color: #b6dcf0");
 	  
 	  theChat.setPrefSize(400, 200);
 	  
 	  background.setFill(Color.rgb(182, 220, 240));
 	  background.setLayoutX(0);
 	  background.setLayoutY(0);
 	  
 	  theChatContainer.getChildren().add(background);
      
      VBox window = new VBox();
      window.getChildren().addAll(theChat, ta, status);

      // Create a scene and place it in the stage
      Scene scene = new Scene(window, 400, 400);
      primaryStage.setMinWidth(400);
      primaryStage.setMinHeight(300);
      
      primaryStage.setTitle("Client " + client + " (" + chatNames[client] + ")"); // Set the stage title
      primaryStage.setScene(scene); // Place the scene in the stage
      primaryStage.show(); // Display the stage
      
      ta.setOnKeyPressed(e -> {
    	  
    	 if (e.getCode() == KeyCode.ENTER) {
    	  
        String msg = ta.getText();
        
        	if (msg != null && !(msg.isBlank())) {
        		
        		String text = client + "𝀽" + msg; 
            	connection.send(text);
        
        	} //if (msg != null)
        	
			ta.clear();
        
    	 } //if (e.getCode() == KeyCode.ENTER)
        
      }); //ta.setOnKeyPressed(e -> {
      
      try {
          // Create a socket to connect to the server
          Socket socket = new Socket("localhost", port + client);

          connection = new DirectStringTransmitter(socket);
          
          updateStatus("Server connected!");

     } catch (IOException ex) {
   	  
          ta.appendText(ex.toString() + '\n');
          
     } //try {...} catch (IOException ex)
     
     new Thread(() -> {
   	 
   	  while (true) {
   		  
   		  message = connection.receive();
   		  
   		  String[] info = message.split(regex);
   		  
   		  int client = Integer.valueOf(info[0]);
   		  String text = info[1];
        	 
        	 Platform.runLater(() -> {
  				
                 updateStatus("Received: " + chatNames[client] + ": " + text);
                 makeChatBubble(text, client);
                 
            });
   		  
   	  } //while (true)
   	  
     }).start();

  } //public void start(Stage primaryStage)
  
  private static void updateStatus(String text) {
	  
	  Runnable update = () -> {
		  
		  status.setText(text);
		  
	  };
	  
	  Platform.runLater(update);
	  
  }
	    
  private static void makeChatBubble(String message, int client) {
	  
	  Label textMessage = new Label(chatNames[client] + ": " + message);
	  
	  textMessage.setLayoutX(15);
	  textMessage.setLayoutY((25 * amountOfMessages) + 2.5);
	  
	  theChatContainer.getChildren().add(textMessage);
	  
	  theChatContainer.applyCss();
	  theChatContainer.layout();
	  
	  Rectangle box = new Rectangle();
	  box.setHeight(20);
	  box.widthProperty().bind(textMessage.widthProperty().add(10));
	  
	  box.setLayoutX(10);
	  box.setLayoutY(25 * amountOfMessages);
	  box.setFill(bubbleColors[client]);
	  
	  Circle first = new Circle(10);
	  first.setCenterX(10);
	  first.setCenterY((25 * amountOfMessages) + 10);
	  first.setFill(bubbleColors[client]);
	  
	  Circle second = new Circle(10);
	  second.centerXProperty().bind(textMessage.widthProperty().add(20));
	  second.setCenterY((25 * amountOfMessages) + 10);
	  second.setFill(bubbleColors[client]);
	  
	  theChatContainer.getChildren().addAll(first, second, box);
	  textMessage.toFront();
	  
	  theChat.setContent(theChatContainer);
	  
	  // Increase amount of messages!
	  amountOfMessages++;
	  
  } //private static void makeChatBubble(String message, int client)

  public static void main(String[] args) {

       launch(args);

  } //public static void main(String[] args)

} //public class ProgramAClient extends Application


