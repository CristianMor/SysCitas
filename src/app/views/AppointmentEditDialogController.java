package app.vies;

import app.models.Appointment;
import app.utils.DateUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AppointmentEditDialogController {
    @FXML
    private TextField nombreField;
    @FXML
    private TextField contactoField;
    @FXML
    private TextField fechaField;


    private Stage dialogStage;
    private Appointment appointment;
    private boolean okClicked = false;

    /*
     * Inicializa la clase del controlador. Este método se llama automáticamente
     * después de que el archivo fxml ha sido cargado.
     */

    @FXML
    private void initialize() {

    }

    /* 
     * Establece el escenario de este diálogo.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /*
     * Establece la persona a editar en el diálogo.
     * 
     * @param person
     */
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;

        nombreField.setText(appointment.getNombre());
        contactoField.setText(appointment.getContacto());
        fechaField.setText(DateUtil.format(appointment.getFecha()));
        fechaField.setPromptText("dd-mm-yyyy");
    }

    /*
     * Devuelve true si el usuario ha pulsado OK, false en caso contrario.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /*
     * Llamada cuando el usuario pulsa ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            appointment.setNombre(nombreField.getText());
            appointment.setContacto(contactoField.getText());
            appointment.setFecha(DateUtil.parse(fechaField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /*
     * Llamada cuando el usuario pulsa cancelar.
     *
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /*
     * Valida la entrada del usuario en los campos de texto. 
     *
     * @return true si el input es valido 
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (nombreField.getText() == null || nombreField.getText().length() == 0) {
            errorMessage += "No valid first name!\n"; 
        }
        if (contactoField.getText() == null || contactoField.getText().length() == 0){
          errorMessage += "No valido contacto";
        }
        /* if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
             errorMessage += "No valid postal code!\n"; 
         } else {
        //     // try to parse the postal code into an int.
        //     try {
        //         Integer.parseInt(postalCodeField.getText());
        //     } catch (NumberFormatException e) {
        //         errorMessage += "No valid postal code (must be an integer)!\n"; 
        //     }
        // }
        // */

        if (fechaField.getText() == null || fechaField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(fechaField.getText())) {
                errorMessage += "No valid fecha. Use the format dd-mm-yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Muestra el error de mensaje.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
