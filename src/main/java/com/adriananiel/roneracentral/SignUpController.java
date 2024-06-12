package com.adriananiel.roneracentral;

import com.adriananiel.roneracentral.Clases.Usuario;
import com.adriananiel.roneracentral.Clases.ValidUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SignUpController {

    @FXML
    private Hyperlink btnSignIn;
    @FXML
    public AnchorPane VentanaSignUp;
    @FXML
    public TextField textUsername;
    @FXML
    public TextField textGmail;
    @FXML
    public PasswordField textPassword;
    @FXML
    public PasswordField textConfirmPassword;

/*
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
                    crea.format("%s\r\n%s\r\n%s\r\n%s",
                            "Username="+textUsername.getText(),
                            "Number="+textNumber.getText(),
                            "Password="+textPassword.getText(),
                            "ConfirmPassword="+textConfirmPassword.getText());
                    crea.close();
                    System.out.println("Archivo creado");
                }
            } catch (Exception e) {
                System.out.println("No se pudo crear el archivo");

            }
        }
    }


 */

    public void CrearCuenta() throws IOException, ClassNotFoundException {
        String name = textUsername.getText();
        String email = textGmail.getText();
        String password = textPassword.getText();

        if (name == null || name.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty()){
            //loginMessageLabel.setText("Revisa tus credenciales" );
            System.out.println("Revisa tus credenciales");
        } else {
            
            ValidUser userManager = new ValidUser();
            Usuario usuario = null;
            try {
                usuario = userManager.valid(email, password);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (usuario == null) {
                //loginMessageLabel.setText("Registrando a " + name);
                System.out.println("Registrando a " + name);
                Usuario user = null;
                try {
                    user = new Usuario(name, email, password);
                } catch (Exception e1) {
                    System.out.println("error: registrar cliente");
                    e1.printStackTrace();
                }
                Usuario.Create(user);
            } else {
                //loginMessageLabel.setText("Ya estás registrado" );
                System.out.println("Ya estás registrado");
            }
        }
    }

    @FXML
    public void eventbtnCreateAccount() throws IOException, ClassNotFoundException {
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