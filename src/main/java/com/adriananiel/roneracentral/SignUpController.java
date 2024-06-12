package com.adriananiel.roneracentral;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.io.File;
import java.io.IOException;
import java.util.Formatter;

public class SignUpController {

    @FXML
    private Hyperlink btnSignIn;
    @FXML
    public AnchorPane VentanaSignUp;
    @FXML
    public TextField textUsername;
    @FXML
    public TextField textNumber;
    @FXML
    public PasswordField textPassword;
    @FXML
    public PasswordField textConfirmPassword;

    String barra = File.separator;
    String ubicacion = System.getProperty("user.dir") + barra + "BaseDatos"+ barra + "User"+ barra;

    private void CrearCuenta() {
        String archivo = textUsername.getText() + ".registros";
        File crea_ubicacion = new File(ubicacion);
        File crea_archivo = new File(ubicacion + archivo);
        System.out.println(archivo);

        if (textUsername.getText().isEmpty() && textNumber.getText().isEmpty() && textPassword.getText().isEmpty() && textConfirmPassword.getText().isEmpty()) {
            System.out.println("No llenaste ningún campo");

        if (textUsername.getText().isEmpty()) {
            System.out.println("No llenaste el campo Username");
            }

        if (textNumber.getText().isEmpty()) {
            System.out.println("No llenaste el campo Number");
            }

        if (textPassword.getText().isEmpty()) {
            System.out.println("No llenaste el campo Password");
            }

        if (textConfirmPassword.getText().isEmpty()) {
            System.out.println("No llenaste el campo Confirm Password");
            }

        } else {

            try {
                if (crea_archivo.exists()) {
                    System.out.println("El registro ya existe");
                } else {

                    crea_ubicacion.mkdirs();

                    Formatter crea = new Formatter(ubicacion + archivo);
                    crea.format("%s\r\n%s\r\n%s",
                            "Username="+textUsername.getText(),
                            "Number="+textNumber.getText(),
                            "Password="+textPassword.getText());
                    crea.close();
                    System.out.println("Archivo creado");
                }
            } catch (Exception e) {
                System.out.println("No se pudo crear el archivo");

            }
        }
    }

    @FXML
    public void eventbtnCreateAccount() throws IOException {
        CrearCuenta();
        AnchorPane ventanaSignInFXML = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        VentanaSignUp.getChildren().setAll(ventanaSignInFXML);
    }

    @FXML
    public void eventBtnSignIn(MouseEvent mouseEvent) throws IOException {
        AnchorPane ventanaSignInFXML = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        VentanaSignUp.getChildren().setAll(ventanaSignInFXML);
    }
}