package app.views;

import app.Main;
import app.models.Appointment;
import app.utils.DateUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    
    // Limpiar detalles de la cita.
    showAppointment(null);
    // Escuchar los cambios de selección y mostrar los detalles de la persona cuando se cambian.
    appointmentTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showAppointment(newValue));
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

  /*
   * Rellena todos los campos de texto para mostrar detalles sobre la cita.
   * Si la persona especificada es nula, se borran todos los campos de texto.
   * 
   * @param cita la cita o null
   */ 
   public void showAppointment(Appointment cita){
    if(cita != null){
      // Rellena las etiquetas con información del objeto cita.
      nombreLabel.setText(cita.getNombre());
      contactoLabel.setText(cita.getContacto());

      // TODO: ¡Necesitamos una forma de convertir el cumpleaños en una cadena!
      fechaLabel.setText(DateUtil.format(cita.getFecha()));
    }else{
      nombreLabel.setText("");
      contactoLabel.setText("");
      fechaLabel.setText("");
    }
  }

  /* 
   * Se ejecuta cuando el usuario pulsa el botón de eliminar.
   */
  @FXML
  private void handleDeleteAppointment(){
    int selectedIndex = appointmentTable.getSelectionModel().getSelectedIndex();
    if(selectedIndex >= 0){
      appointmentTable.getItems().remove(selectedIndex);
    }else{
      // Nada seleccionado
      Alert alerta = new Alert(AlertType.WARNING);
      alerta.initOwner(main.getPrimaryStage());
      alerta.setTitle("No selection");
      alerta.setHeaderText("No Cita Selected");
      alerta.setContentText("Plase select a cita en la tabla");
      alerta.showAndWait();
    }
  }
}
