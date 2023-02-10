#!/bin/bash
javac -d /Users/dev-mobile/Workspace/JavaProjects/SysCitas/bin --module-path /Users/dev-mobile/Downloads/openjfx-17/javafx-sdk-19.0.2.1/lib --add-modules javafx.controls,javafx.fxml ./src/app/Main.java ./src/app/models/Appointment.java ./src/app/views/AppointmentViewController.java ./src/app/utils/DateUtil.java ./src/app/views/AppointmentEditDialogController.java

java -cp bin --module-path /Users/dev-mobile/Downloads/openjfx-17/javafx-sdk-19.0.2.1/lib --add-modules javafx.controls,javafx.fxml app.Main
