package application;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
	
	public void start(Stage primaryStage) {
	 
	  Menu menu= new Menu();
	  menu.start(primaryStage);
  }
	public static void main(String[] args) {
		launch(args);
	}
	
}