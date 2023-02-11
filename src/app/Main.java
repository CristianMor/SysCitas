package app;

import app.controllers.Controller;
import app.models.AppointmentModel;
import app.models.classes.Appointment;

import java.io.File;
import java.io.IOException;
import java.lang.Exception;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

  private BorderPane root;
  private Controller controller = new Controller();
  private AppointmentModel model = new AppointmentModel();
  private Stage primaryStage;

  @Override
  public void start(Stage primaryStage) throws Exception{
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle("Inicio");

    initRootLayout();
  } 

  //Cargar diseño raíz desde archivo fxml
  public void initRootLayout(){
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(new File("src/app/views/view.fxml").toURI().toURL());
      root = (BorderPane) loader.load();

      // Show the scene containing the root layout.
      Scene scene = new Scene(root);
      primaryStage.setScene(scene);
      primaryStage.setResizable(false);
      primaryStage.show();

    } catch (IOException ioe){
      ioe.printStackTrace();
    }
  }

  public static void main(String[] args) {
    System.out.println(" ** Inicio programa ** ");
    launch(args);
  }

}
