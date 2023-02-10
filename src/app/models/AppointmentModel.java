package app.models;

import app.models.classes.Appointment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AppointmentModel {

  private ObservableList<Appointment> data = FXCollections.observableArrayList();

  public ObservableList<Appointment> getData(){
    return data;
  }

  public void addAppointment(String username, String appointmentTime){
    data.add(new Appointment(username, appointmentTime));
    // Aquí deberías realizar una petición HTTP para agregar una cita a la base de datos.
    // Una vez que tengas la respuesta, puedes agregar la cita a la lista `data`.
  }

  public void updateAppointment(Integer index, String username, String appointmentTime){
    // Actualizar las propiedades del elemento 
    Appointment appointment = data.get(index);
    appointment.setUsername(username);
    appointment.setAppointmentTime(appointmentTime);

    // Sustituir el elemento antiguo por el elemento actualizado
    data.set(index, appointment);

    // Aquí deberías realizar una petición HTTP para agregar una cita a la base de datos.
    // Una vez que tengas la respuesta, puedes actualizar la cita a la lista `data`.
  }

  public void deleteAppointment(Appointment appointment){
    System.out.println(" ** Eliminando cita **");
    data.remove(appointment);
    // Aquí deberías realizar una petición HTTP para agregar una cita a la base de datos.
    // Una vez que tengas la respuesta, puedes eliminar la cita a la lista `data`.
  }
}
