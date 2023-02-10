package app.controllers;
 
import app.Main;
import app.models.AppointmentModel;
import app.models.classes.Appointment;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
  private void addAppointment(){
    String username =  usernameInput.getText();
    String appointmentTime = appointmentTimeInput.getText();

    model.addAppointment(username, appointmentTime);
    table.refresh();
    usernameInput.clear();
    appointmentTimeInput.clear();
  }

  @FXML
  private void updateAppointment(){
    Appointment appointment = table.getSelectionModel().getSelectedItem();
    if(appointment == null){
      return;
    }

    int index = data.indexOf(appointment);
    String username = usernameInput.getText();
    String appointmentTime = appointmentTimeInput.getText();

    model.updateAppointment(index, username, appointmentTime);
    table.refresh();
    usernameInput.clear();
    appointmentTimeInput.clear();
  }

  @FXML
  private void deleteAppointment(){

    Appointment appointment = table.getSelectionModel().getSelectedItem();

    if(appointment == null){
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
