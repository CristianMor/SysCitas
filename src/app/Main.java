package app;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {

  @Override 
  public void start(Stage primaryState) throws Exception{
    Parent root = FXMLLoader.load(new File("src/app/sample.fxml").toURI().toURL());
    primaryState.setTitle("Hola mundo");
    primaryState.setScene(new Scene(root, 300, 275));
    primaryState.show();
  }

  public static void main(String[] args) { launch(args); }
  
}
