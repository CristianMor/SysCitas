package app.models;

import java.time.LocalDate;
//import javafx.beans.property.IntegerPropery;
import javafx.beans.property.ObjectProperty;
//import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Appointment {

  private final StringProperty nombre;
  private final StringProperty contacto; 
  private final ObjectProperty<LocalDate> fecha;


  /* Contructor por defecto */
  public Appointment() {
    this(null);
  }

  /* Constructor con algunos datos iniciales
   * @param nombre
   */
  public Appointment(String nombre) {
    this.nombre = new SimpleStringProperty(nombre);

    // Algunos datos ficticios iniciales, sólo para pruebas prácticas.
    this.contacto = new SimpleStringProperty("+52 669 147 78 68");
    this.fecha = new SimpleObjectProperty<LocalDate>(LocalDate.of(2023, 2, 21));
  }

  public String getNombre(){
    return nombre.get();
  }

  public void setNombre(String nombre){
    this.nombre.set(nombre);
  }

  public StringProperty nombreProperty(){
    return nombre;
  }

  public String getContacto(){
    return contacto.get();
  }

  public void setContacto(String contacto){
    this.contacto.set(contacto);
  }

  public StringProperty contactoProperty(){
    return contacto;
  }

  public LocalDate getFecha(){
    return fecha.get();
  }

  public void setFecha(LocalDate fecha){
    this.fecha.set(fecha);
  }

  public ObjectProperty<LocalDate> fechaProperty(){
    return fecha;
  }
}
