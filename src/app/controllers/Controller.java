package app.controllers;
 
import app.Main;
import app.models.AppointmentModel;
import app.models.classes.Appointment;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Controller {
  @FXML
  private TableView<Appointment> table;
  @FXML
  private TableColumn<Appointment, String> usernameColumn;
  @FXML
  private TableColumn<Appointment, String> appointmentTimeColumn;
  @FXML 
  private TextField usernameInput;
  @FXML
  private TextField appointmentTimeInput;

  private ObservableList<Appointment> data = FXCollections.observableArrayList(); 
  private AppointmentModel model = new AppointmentModel();

  public void initialize(){

    try{
    System.out.println(" ** Obteniendo informacion de la bd ** ");
      model.getAppointments();
    }catch(Exception e){ }

    usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
    appointmentTimeColumn.setCellValueFactory(cellData -> cellData.getValue().appointmentTimeProperty());

    // Limpiar detalles de la cita.
    showAppointment(null);

    // Escuchar los cambios de selección y mostrar los detalles de la persona cuando se cambian.
    table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showAppointment(newValue));

    data = model.getData();
    table.setItems(data);
  }

  @FXML 
  private void getData(){
    try{

    }catch(Exception e){

    }
  }

  @FXML
  private void addAppointment(){

    String username =  usernameInput.getText();
    String appointmentTime = appointmentTimeInput.getText();

    boolean canContinue = username == "" || appointmentTime == "" ? false : true;

    if(canContinue){
      this.model.addAppointment(username, appointmentTime);
      table.refresh();
      usernameInput.clear();
      appointmentTimeInput.clear();
    }else{
      Alert alerta = new Alert(Alert.AlertType.INFORMATION);
      alerta.setTitle("CITAS_SYS");
      alerta.setHeaderText("Crear nueva cita");
      alerta.setContentText("No puede crear citas en blanco");

      alerta.showAndWait();
    }


  }

  @FXML
  private void updateAppointment(){
    Appointment appointment = table.getSelectionModel().getSelectedItem();

    if(appointment == null){
  
      Alert alerta = new Alert(Alert.AlertType.INFORMATION);
      alerta.setTitle("CITAS_SYS");
      alerta.setHeaderText("Actualizar cita");
      alerta.setContentText("No ha seleccionado cita");

      alerta.showAndWait();
      return;
    }

    int index = data.indexOf(appointment);
    String username = usernameInput.getText();
    String appointmentTime = appointmentTimeInput.getText();
    String idcita = appointment.getIdCita();

    model.updateAppointment(index, username, appointmentTime, idcita);
    table.refresh();
    usernameInput.clear();
    appointmentTimeInput.clear();
  }

  @FXML
  private void deleteAppointment(){

    Appointment appointment = table.getSelectionModel().getSelectedItem();

    if(appointment == null){
      Alert alerta = new Alert(Alert.AlertType.INFORMATION);
      alerta.setTitle("CITAS_SYS");
      alerta.setHeaderText("Eliminar cita");
      alerta.setContentText("No ha seleccionado cita");

      alerta.showAndWait();
      return;
    }

    model.deleteAppointment(appointment);
    table.refresh();
    usernameInput.clear();
    appointmentTimeInput.clear();
  }

  private void showAppointment(Appointment appointment){
    if(appointment != null ){
      // Rellena los inputs con información del objeto cita.
      usernameInput.setText(appointment.getUsername());
      appointmentTimeInput.setText(appointment.getAppointmentTime());
    }else{
      usernameInput.setText("");
      appointmentTimeInput.setText("");
    }
  }

}
