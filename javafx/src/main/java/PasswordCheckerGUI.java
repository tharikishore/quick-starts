
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.Insets;



public class PasswordCheckerGUI extends Application implements EventHandler <ActionEvent>
{
	
	 Button btn1;
	 Button btn2;
	 Button btn3;
	 Button btnOk;
	 TextField center1Pass;
	 TextField center2Pass;
	 Scene scene;
	 Scene scene2;
	 Scene scene3;
	 Scene scene4;
	 Scene scene5;
	 Scene scene6;
	 Scene scene7;
	 Stage window;
	 HBox hboxSouth1;
	 PasswordChecker newPasswordChecker = new PasswordChecker();
	 char exceptionChooser = 'x';
	 VBox scene3HBox;
	public void start(Stage primaryStage) 
	{
	   // Group root = new Group(); 
	  window =primaryStage;
	    
	    
	    Label north1 = new Label("Use the following rules when creating your passwords");
	    Label north2 = new Label("1. Length must be greater than 8");
	    Label north3 = new Label("2. Must contain at least one upper case alpha character");
	    Label north4 = new Label("3. Must contain at least one lower case alpha character");
	    Label north5 = new Label("4. Must contain at least one numeric character");
	    Label north6 = new Label("5. May not have more than 2 of the same character in sequence");
	   
	    VBox vboxNorth = new VBox();     // sets spacing
	    vboxNorth.getChildren().addAll(north1, north2, north3,north4,north5,north6);
	    
	    
	    Label center1 = new Label("Password ");
	    Label center2 = new Label("Re-type Password ");
	    
	    center1Pass = new TextField();
	    center1Pass.setMinWidth(10);
	    center2Pass = new TextField();
	    center2Pass.setMinWidth(10);
	    
	    HBox hboxCenter1 = new HBox();
	    
	    
	    hboxCenter1.getChildren().addAll(center1, center1Pass);
	    hboxCenter1.setPadding(new Insets(45, 50, 10, 150));
	    
	    HBox hboxCenter2 = new HBox();
	    
	    
	    hboxCenter2.getChildren().addAll(center2, center2Pass);
	    hboxCenter2.setPadding(new Insets(1, 50, 1, 125));
	    
	    
	    VBox vboxCenter = new VBox();     // sets spacing
	    vboxCenter.getChildren().addAll(hboxCenter1,hboxCenter2);
	    //----------------------------------
	    
	    btn1 = new Button("Check Password");
	    btn1.setOnAction(this);
	    btn1.setOnAction(this);
	    btn2 = new Button("Check Passwords in File");
	    btn2.setOnAction(this);
	    btn3 = new Button("Exit");
	    btn3.setOnAction(this);
	    
	    
	    
	    hboxSouth1 = new HBox();
	    hboxSouth1.getChildren().addAll(btn1, btn2, btn3);
	    hboxSouth1.setPadding(new Insets(1, 1, 2, 113));
	    
	    
	    BorderPane border1 = new BorderPane();
	    border1.setCenter(vboxCenter);
	    border1.setTop(vboxNorth);	
//	    border1.setRight(btn3);
	    border1.setBottom(hboxSouth1);
//	    border1.setLeft(btn5);

	    scene = new Scene(border1, 500, 400);
	   
	    
	    
	    
	    
	    //-----------------------------------------------
	    VBox scene2HBox = new VBox();
	    VBox scene2HBoxSub1 = new VBox();
	    VBox scene2HBoxSub2 = new VBox();
	    scene2HBoxSub1.setPadding(new Insets(10, 10, 10, 70));
	    scene2HBoxSub2.setPadding(new Insets(10, 10, 10, 175));
	    
	   // scene2HBox.setPadding(new Insets(10, 10, 10, 113));
	    Label scene2Label = new Label("UnmatchedException: The password does not match.");
	    btnOk = new Button("OK");
	    btnOk.setOnAction(this);
	    
	    scene2HBoxSub2.getChildren().addAll(btnOk);
	    scene2HBoxSub1.getChildren().addAll(scene2Label);
	    scene2HBox.getChildren().addAll(scene2HBoxSub1, scene2HBoxSub2);
	    scene2 = new Scene(scene2HBox, 400, 80);
	   
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    //----------!!!!
//	    VBox scene3HBox = new VBox();
//	    VBox scene3HBoxSub1 = new VBox();
//	    VBox scene3HBoxSub2 = new VBox();
//	    scene3HBoxSub1.setPadding(new Insets(10, 10, 10, 70));
//	    scene3HBoxSub2.setPadding(new Insets(10, 10, 10, 175));
//	    
//	   // scene2HBox.setPadding(new Insets(10, 10, 10, 113));
//	    Label scene3Label = new Label("NoUpperAlphaException: The password must contain at least one uppercase alphabetic character.");
//	    
//	    
//	    scene3HBoxSub2.getChildren().addAll(btnOk);
//	    scene3HBoxSub1.getChildren().addAll(scene3Label);
//	    scene3HBox.getChildren().addAll(scene3HBoxSub1, scene3HBoxSub2);
//	    scene3 = new Scene(scene3HBox, 400, 80);

	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	
	    //scene.addEventFilter(arg0, arg1);
	    //vbox1.setAlignment(CENTER);
	    primaryStage.setScene(scene);
	    primaryStage.setTitle("Password Checker");
	    primaryStage.show();
	}
	
	public void handle(ActionEvent e) 
	{
		if (e.getSource()== btn1)
		{
			
			String checkPassword1 = center1Pass.getText();
			 String reCheckPassword1 = center2Pass.getText();
			 
				if((checkPassword1.compareTo(reCheckPassword1))==0)
				{
					String message= null;
					try
					{boolean placeHolder;
						placeHolder = newPasswordChecker.isValidPassword(center1Pass.getText());
					}
					catch(NoUpperAlphaException e2)
					{  message = "NoUpperAlphaException: The password must contain at least one uppercase alphabetic character.";
						//window.setScene(scene3);
						e2.printStackTrace();
					}
					catch(NoLowerAlphaException e3)
					{  message = "NoLowerAlphaException: The password must contain at least one lowercase alphabetic character.";
						//window.setScene(scene4);
						e3.printStackTrace();
					}
					catch(NoDigitException e4)
					{
						e4.printStackTrace();
					}
					catch(LengthException e5)
					{
						e5.printStackTrace();
					}
					catch(InvalidSequenceException e6)
					{
						e6.printStackTrace();
					}
					    scene3HBox = new VBox();
					    VBox scene3HBoxSub1 = new VBox();
					    VBox scene3HBoxSub2 = new VBox();
					    scene3HBoxSub1.setPadding(new Insets(10, 10, 10, 70));
					    scene3HBoxSub2.setPadding(new Insets(10, 10, 10, 175));
					    
					   // scene2HBox.setPadding(new Insets(10, 10, 10, 113));
					    Label scene3Label = new Label(message);
					    
					    
					    scene3HBoxSub2.getChildren().addAll(btnOk);
					    scene3HBoxSub1.getChildren().addAll(scene3Label);
					    scene3HBox.getChildren().addAll(scene3HBoxSub1, scene3HBoxSub2);
					    scene3 = new Scene(scene3HBox, 400, 80);
					    window.setScene(scene3);
				}
				else
				{
					window.setScene(scene2);
					try {
						passwordDoesNotMatch();
					} catch (UnmatchedExcpetion e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			
		
		}
		
		if (e.getSource()== btn2)
		{
			
		}
		if (e.getSource()== btn3)
		{
			
		}
		if (e.getSource()== btnOk)
		{
			window.setScene(scene);
		}
	}
	public void passwordDoesNotMatch()throws UnmatchedExcpetion
	{
		throw new UnmatchedExcpetion("UnmatchedException: The password does not match");
	
	}
	public void passwordNoUpperAlphaException()throws NoUpperAlphaException
	{
		throw new NoUpperAlphaException("NoUpperAlphaException: The password must contain at least one uppercase alphabetic character.");
	
	}
	public static void main(String[] args)
	{
	 launch(args);
	}
}
