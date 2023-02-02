package app.views;

import app.Main;
import app.models.Appointment;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AppointmentViewController{

  @FXML
  private TableColumn<Appointment, String> nombreColumn;
  @FXML
  private TableView<Appointment> appointmentTable;
    
  @FXML
  private Label nombreLabel;
  @FXML
  private Label contactoLabel;
  @FXML 
  private Label fechaLabel;

  // Referencia a la aplicación principal
  private Main main;

  /* El constructor.
   * El constructor es llamado antes del método initialize().
   */
  public AppointmentViewController(){

  }

  /*
   * Inicializa la clase del controlador. Este método es llamado automáticamente
   * después de que el archivo fxml ha sido cargado.
   */
  @FXML
  public void initialize(){
    // Inicializar la tabla persona con la columna.
    nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
  }

  /*
   * Es llamada por la aplicación principal para devolver una referencia a sí misma.
   * @param main
   */
  public void setMain(Main main){
    this.main = main;

    // Añadir los datos de la lista de observables a la tabla
    appointmentTable.setItems(main.getCitaData());
  }
}
