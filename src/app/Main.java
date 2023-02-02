package app;

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  private Stage primaryStage;
  private BorderPane rootView;

  @Override 
  public void start(Stage primaryStage){
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle("SysCitas");

    initRootLayout();
    showAppointmentView();
  }

  //Cargar diseño raíz desde archivo fxml
  public void initRootLayout(){
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(new File("src/app/views/RootView.fxml").toURI().toURL());
      rootView = (BorderPane) loader.load();

      // Show the scene containing the root layout.
      Scene scene = new Scene(rootView);
      primaryStage.setScene(scene);
      primaryStage.show();

    } catch (IOException ioe){
      ioe.printStackTrace();
    }
  }

  // Muestra la vista general de las citas dentro del root layout
  public void showAppointmentView(){
    try {
      // Cargar citas view
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(new File("src/app/views/AppointmentView.fxml").toURI().toURL());
      AnchorPane appointmentView = (AnchorPane) loader.load();

      // Coloque la vista general de citas en el centro de la disposición de raíces.
      rootView.setCenter(appointmentView);

    } catch (IOException ioe) {
      //TODO: handle exception
      ioe.printStackTrace();
    }
  }

  /* Devuelve el escenario principal.
   * @return
   */
  public Stage getPrimaryStage(){
    return primaryStage;
  }

  public static void main(String[] args) { launch(args); }
  
}
