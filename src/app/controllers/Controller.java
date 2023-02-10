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
    usernameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    appointmentTimeColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

    data = model.getData();
    table.setItems(data);
  }

  @FXML
  private void addApointment(){
    String username =  usernameInput.getText();
    String appointmentTime = appointmentTimeInput.getText();

    model.addPerson(username, appointmentTime);
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

    String username = usernameInput.getText();
    String appointmentTime = appointmentTimeInput.getText();

    model.updateAppointment(username, appointmentTime);
    table.refresh;
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
}
