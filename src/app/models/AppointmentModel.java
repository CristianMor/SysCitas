public class AppointmentModel {
  private ObservableList<Appointment> data = FXCollections.observableArrayList();

  public ObservableList<Appointment> getData(){
    return data;
  }

  public void addAppointment(String username, String appointmentTime){
    // Aquí deberías realizar una petición HTTP para agregar una persona a la base de datos.
    // Una vez que tengas la respuesta, puedes agregar la persona a la lista `data`.
  }

  public void updateAppointment(String username, String appointmentTime){
    // Aquí deberías realizar una petición HTTP para agregar una persona a la base de datos.
    // Una vez que tengas la respuesta, puedes actualizar la persona a la lista `data`.
  }

  public void deleteAppointment(Appointment Appointment){
    // Aquí deberías realizar una petición HTTP para agregar una persona a la base de datos.
    // Una vez que tengas la respuesta, puedes eliminar la persona a la lista `data`.
  }
}
