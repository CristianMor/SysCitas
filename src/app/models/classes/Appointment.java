package app.models.classes;

import javafx.beans.property.SimpleStringProperty;

public class Appointment {

  private SimpleStringProperty username;
  private SimpleStringProperty appointmentTime;

  public Appointment(String username, String appointmentTime){
    this.username = new SimpleStringProperty(username);
    this.appointmentTime = new SimpleStringProperty(appointmentTime);
  }

  public void setUsername(String username){
    this.username.set(username);
  };

  public String getUsername(){
    return this.username.get();
  }

  public SimpleStringProperty usernameProperty(){
    return username;
  }

  public void setAppointmentTime(String appointmentTime){
    this.appointmentTime.set(appointmentTime);
  }

  public String getAppointmentTime(){
    return this.appointmentTime.get();
  }

  public SimpleStringProperty appointmentTimeProperty(){
    return appointmentTime;
  }
}
