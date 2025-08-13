package Exercise32_03;

import javafx.animation.PathTransition; 
import javafx.application.Application; 
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FlagRisingAnimation extends Application {
	
	 PathTransition pt;
	 
	 Runnable animationTask = () -> {
		 
         pt.play();
		 
	 };
	 
	 Thread animationThread = new Thread(animationTask);

     @Override // Override the start method in the Application class
     public void start(Stage primaryStage) {

          // Create a pane
          Pane pane = new Pane();

          // Add an image view and add it to pane
          ImageView imageView = new ImageView("file:\\D:\\Hopefully,_we_can_rename_Workspaces\\Module6Stuff\\src\\Exercise32_03\\image\\us.gif");
          pane.getChildren().add(imageView);
          
          Line path = new Line(100, 200, 100, 0);
          
          Line pole = new Line(10, 200, 10, 0);
          pole.setStroke(Color.BLACK);
          pole.setStrokeWidth(15);
          
          pane.getChildren().add(pole);

          // Create a path transition
          pt = new PathTransition(
               Duration.millis(10000),
               path,
               imageView
          );
          
          pt.setCycleCount(5);
     	  animationThread.start();

          // Create a scene and place it in the stage
          Scene scene = new Scene(pane, 250, 200);
          primaryStage.setTitle("FlagRisingAnimation"); // Set the stage title
          primaryStage.setScene(scene); // Place the scene in the stage
          primaryStage.show(); // Display the stage          

     } //public void start(Stage primaryStage)
	           
     public static void main (String[] args) {
    	 
    	 launch(args);
    	 
     } //public static void main (String[] args)
     
} //public class FlagRisingAnimation extends Application