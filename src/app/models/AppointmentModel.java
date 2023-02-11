package app.models;

import app.models.classes.Appointment;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import org.json.*;

public class AppointmentModel {

  private ObservableList<Appointment> data = FXCollections.observableArrayList();;

  public ObservableList<Appointment> getData(){
    return data;
  }

  public void getAppointments() throws Exception{

    URL url = new URL("http://localhost/backend-dates/api/leer");
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
    connection.setDoOutput(true);
    connection.setConnectTimeout(5000);
    connection.setReadTimeout(5000);
    connection.connect();

    InputStream strm = connection.getInputStream();
    byte []arrStream = strm.readAllBytes();

    String strJson = "";

    for(byte temp: arrStream){
      strJson+=(char) temp;
    }
    connection.disconnect();

    JSONArray json = new JSONArray(strJson);

    for(Object obj: json){
      String username = ((JSONObject) obj).get("username").toString();
      String appointmentTime = ((JSONObject) obj).get("cita").toString();
      String idcita = ((JSONObject) obj).get("idcita").toString();

      data.add(new Appointment(username, appointmentTime, idcita));
    }

  }
  
  public void addAppointment(String username, String appointmentTime){

    try{
      URL url = new URL("http://localhost/backend-dates/api/crear");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setDoOutput(true);

      String urlParameters = "username="+username+"&cita="+appointmentTime;
      byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
      try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
        wr.write(postData);
      }

      BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String inputLine;
      StringBuffer content = new StringBuffer();
      while ((inputLine = in.readLine()) != null) {
        content.append(inputLine);
      }
      in.close();

      JSONObject json = new JSONObject(content.toString());
      String idcita = json.get("resultado").toString();
      System.out.println("El nuevo registro es: "+ idcita);
      data.add(new Appointment(username, appointmentTime, idcita));
      }catch(IOException ioe){

    }
  }

  public void updateAppointment(Integer index, String username, String appointmentTime, String idcita){

    System.out.println(" ** Editando la cita con id " +idcita);
    try{
      URL url = new URL("http://localhost/backend-dates/api/actualizar");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setDoOutput(true);

      StringBuilder sb = new StringBuilder();
      sb.append("idcita=").append(URLEncoder.encode(idcita, "UTF-8"));
      sb.append("&username=").append(URLEncoder.encode( username,"UTF-8"));
      sb.append("&cita=").append(URLEncoder.encode( appointmentTime,"UTF-8"));

      OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
      writer.write(sb.toString());
      writer.flush();

      int responseCode = connection.getResponseCode();

      if (responseCode == HttpURLConnection.HTTP_OK) {
          BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
          String line;
          while ((line = reader.readLine()) != null) {
              System.out.println(line);
              Appointment appointment = data.get(index);
              appointment.setUsername(username);
              appointment.setAppointmentTime(appointmentTime);

            
              data.set(index, appointment);
              System.out.println(" -> Se edito con exito");
          }
          reader.close();

        }
      connection.disconnect();
      }catch(IOException ioe){

    }

  }

  public void deleteAppointment(Appointment appointment){
    System.out.println(" ** Eliminando cita **");
    String idcita = appointment.getIdCita();
  try{
      URL url = new URL("http://localhost/backend-dates/api/eliminar");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setDoOutput(true);

      StringBuilder sb = new StringBuilder();
      sb.append("id=").append(URLEncoder.encode(idcita, "UTF-8"));

      OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
      writer.write(sb.toString());
      writer.flush();

      int responseCode = connection.getResponseCode();

      if (responseCode == HttpURLConnection.HTTP_OK) {
          BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
          String line;
          while ((line = reader.readLine()) != null) {
              System.out.println(" -> Respuesta " +line);
              data.remove(appointment);
          }
          reader.close();
        }
      }catch(IOException ioe){

    }
  }
}
