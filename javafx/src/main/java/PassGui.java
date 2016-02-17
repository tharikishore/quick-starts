
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class PassGui extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage){
		primaryStage.setTitle("Hello");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
        	Stage messageStage = new Stage();
        	int count = 0;
            
            @Override
            public void handle(ActionEvent event) {
            	this.count++;
	        	messageStage.setTitle("Title for Message Window");
		        StackPane pane = new StackPane();
		        pane.getChildren().add(new Label("Hello World: "+count));
		        
	        	Scene helloScene = new Scene(pane, 600, 400);
	        	messageStage.setScene(helloScene);
	        	messageStage.show();
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
	}

}
