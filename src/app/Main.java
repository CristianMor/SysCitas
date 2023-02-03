SE ESCRIBIO EN LA PRIMERA LINEA PERROS
package app;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;

public class Main extends Application {

  private Stage primaryStage;
  private BorderPane rootView;
  // Los datos como lista observable de citas. 
  private ObservableList<Appointment> citaData = FXCollections.observableArrayList();
  
  public Main(){
    citaData.add(new Appointment("Chanchito"));
  }

  /* Devuelve los datos como una lista observable de Citas.
   * @return 
   */
  public ObservableList<Appointment> getCitaData(){
    return citaData;
  }

  @Override 
  public void start(Stage primaryStage){


    console.log("Maz cosas cambiaron ");
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

  console.log("Se agrego un poco como si fuera javascript");
  // Muestra la vista general de las citas dentro del root layout
  public void showAppointmentView(){
    try {
      // Cargar citas view
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(new File("src/app/views/AppointmentView.fxml").toURI().toURL());
      AnchorPane appointmentView = (AnchorPane) loader.load();

      // Coloque la vista general de citas en el centro de la disposición de raíces.
      rootView.setCenter(appointmentView);

      // Dar acceso al controlador a la aplicación principal
      AppointmentViewController controller = loader.getController();
      controller.setMain(this);
    } catch (IOException ioe) {
      //TODO: handle exception
      ioe.printStackTrace();
    }
  }

  /*
   * Abre un diálogo para editar los detalles de la persona especificada. Si el usuario
   * hace clic en Aceptar, los cambios se guardan en el objeto persona proporcionado y se devuelve true
   * es devuelto.
   * 
   * @param persona el objeto persona a editar
   * @return true si el usuario hace clic en OK, false en caso contrario.
   */
    public boolean showAppointmentEditDialog(Appointment appointment) {
      try {
        // Cargar el archivo fxml y crear un nuevo escenario para el diálogo emergente.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new File("src/app/views/AppointmentEditDialog.fxml").toURI().toURL());
        AnchorPane page = (AnchorPane) loader.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Cita");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Establecer la persona en el controlador.
        AppointmentEditDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setAppointment(appointment);

        // Mostrar el diálogo y esperar a que el usuario lo cierre
        dialogStage.showAndWait();

        return controller.isOkClicked();
      } catch (IOException e) {
        e.printStackTrace();
        return false;
      }
    }

  /* Devuelve el escenario principal.
   * @return
   */
  public Stage getPrimaryStage(){
    return primaryStage;
  }

  public static void main(String[] args) { launch(args); }  
  console.log('khjasdhf');
}

