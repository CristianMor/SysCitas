package app.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {
  /*
   * El patr—n de fecha que se utiliza para la conversi—n. 
   * C‡mbielo como desee. 
   * */
  private static final String DATE_PATTERN = "dd-MM-yyyy";

  /*
   * El formateador de fecha. 
   * */
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

  /*
   * Devuelve la fecha dada como una cadena bien formateada. Se utiliza el 
   * {@link DateUtil#DATE_PATTERN}.
   * 
   * @param date la fecha a devolver como cadena
   * @return cadena formateada
   */
  public static String format(LocalDate date){
    if( date == null){
      return null;
    }
    return DATE_FORMATTER.format(date);
  }

  /*
   * Convierte una Cadena en el formato definido {@link DateUtil#DATE_PATTERN} 
   * a un objeto {@link LocalDate}.
   * 
   * Devuelve null si la cadena no ha podido ser convertida.
   * 
   * @param dateString la fecha como String
   * @return el objeto fecha o null si no se pudo convertir
   */
  public static LocalDate parse(String dateString){
    try{
      return DATE_FORMATTER.parse(dateString, LocalDate::from);
    }catch(DateTimeParseException dtpe){
      return null;
    }
  }

  /*
   * Comprueba si la cadena es una fecha v‡lida.
   * 
   * @param dateString
   * @devuelve true si la cadena es una fecha v‡lida
   */
  public static boolean validDate(String dateString){
    // Intenta analizar la cadena
    return DateUtil.parse(dateString) != null;
  }
}
