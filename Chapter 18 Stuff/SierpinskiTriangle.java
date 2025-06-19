import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class SierpinskiTriangle extends Application {
	
	private int order = 0;
	
	static Label currentOrder = new Label ("Current Order: 0");
	
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
    	
        SierpinskiTrianglePane trianglePane = new SierpinskiTrianglePane();
        
        Button PLUS = new Button("+");
        Button MINUS = new Button("-");
        
        currentOrder.setStyle("-fx-text-fill: white;");
        
        EventHandler<ActionEvent> plusEvent = new EventHandler<ActionEvent>() {
        	
            public void handle(ActionEvent e) {
            	
            	if (order < 10) {
            		
            		order++;
            		currentOrder.setText("Current Order: " + order);
            		trianglePane.setOrder(order);
            	
            	}
            	
            }
            
        };
        
        EventHandler<ActionEvent> minusEvent = new EventHandler<ActionEvent>() {
        	
            public void handle(ActionEvent e) {
            	
            	if (order > 0) {
            		
            		order--;
            		currentOrder.setText("Current Order: " + order);
            		trianglePane.setOrder(order);
            	
            	}
            	
            }
            
        };
        
        PLUS.setOnAction(plusEvent);
        MINUS.setOnAction(minusEvent);

        // Pane to hold label, text field, and a button
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(currentOrder, PLUS, MINUS);
        hBox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #0b0c0d;");
        borderPane.setCenter(trianglePane);
        borderPane.setBottom(hBox);

        // Create a scene and place it in the stage
        Scene scene = new Scene(borderPane, 600, 610);
        primaryStage.setTitle("SierpinskiTriangle"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        scene.widthProperty().addListener(ov -> trianglePane.paint());
        scene.heightProperty().addListener(ov -> trianglePane.paint());
        
        trianglePane.setOrder(0);
        
        scene.setOnKeyPressed(event -> {
        	
        	if (event.getCode().equals(KeyCode.EQUALS)) {
        	
            	if (order < 10) {
            		
            		order++;
            		currentOrder.setText("Current Order: " + order);
            		trianglePane.setOrder(order);
            	
            	}
        	
        	}
        	
        	if (event.getCode().equals(KeyCode.MINUS)) {
            	
            	if (order > 0) {
            		
            		order--;
            		currentOrder.setText("Current Order: " + order);
            		trianglePane.setOrder(order);
            	
            	}
        	
        	}
        	
        });
        
        //scene.requestFocus();
                
    }
    
    public static void main(String[] args) {
    	
    	launch(args);
    	
    }

    /** Pane for displaying triangles */
    static class SierpinskiTrianglePane extends Pane {
    	
        private Color[] funny =
    		{Color.RED, Color.ORANGE, Color.YELLOW,
    		Color.GREEN, Color.BLUE, Color.PURPLE};
        
        private int COLOR = 0;
    	
        private int order = 0;

        /** Set a new order */
        public void setOrder(int order) {
        	
            this.order = order;
            paint();
            
        }

        SierpinskiTrianglePane() {
        	
        }

        protected void paint() {
            // Select three points in proportion to the pane size
            Point2D p1 = new Point2D(getWidth() / 2, 10);
            Point2D p2 = new Point2D(10, getHeight() - 10);
            Point2D p3 = new Point2D(getWidth() - 10, getHeight() - 10);

            this.getChildren().clear(); // Clear the pane before redisplay

            displayTriangles(order, p1, p2, p3);
            
        }

        private void displayTriangles(int order, Point2D p1, Point2D p2, Point2D p3) {
        	
            if (order == 0) {
            	//Choose a funny color (:     
            	COLOR = COLOR + 1;
            	
            	if (COLOR > 5) {
            		
            		COLOR = 0;
            		
            	}
                // Draw a triangle to connect three points
                Polygon triangle = new Polygon();
                triangle.getPoints().addAll(
                    p1.getX(), p1.getY(),
                    p2.getX(), p2.getY(),
                    p3.getX(), p3.getY()
                );
                
                triangle.setFill(funny[COLOR]);

                this.getChildren().add(triangle);
                
            } else {
            	
                // Get the midpoint on each edge in the triangle
                Point2D p12 = p1.midpoint(p2);
                Point2D p23 = p2.midpoint(p3);
                Point2D p31 = p3.midpoint(p1);

                // Recursively display three triangles
                displayTriangles(order - 1, p1, p12, p31);
                displayTriangles(order - 1, p12, p2, p23);
                displayTriangles(order - 1, p31, p23, p3);
                
            }
            
        }
        
    }
    
}
