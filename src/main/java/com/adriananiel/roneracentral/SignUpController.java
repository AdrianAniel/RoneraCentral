package com.adriananiel.roneracentral;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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
    @FXML
    public Pane PanePasswordInvalid;

    //LoginSystem loginSystem = new LoginSystem();

    public void Registrar() throws IOException {
        String username = textUsername.getText();
        String password = textPassword.getText();
        String email = textGmail.getText();
        String confirmPassword = textConfirmPassword.getText();

        // Verificando si las contraseñas son iguales y entonces registra el usuario
        if (password.equals(confirmPassword)) {
            System.out.println("Contraseñas iguales");
            Registro.registrarUsuario(new Usuario(username, password, email));

            try {
                AnchorPane ventanaSignInFXML = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
                VentanaSignUp.getChildren().setAll(ventanaSignInFXML);

            } catch (IOException e) {
                System.err.println("Error al registrar el usuario: " + e.getMessage());
            }

        } else {
            Pane PanePasswordInvalidFXML = FXMLLoader.load(getClass().getResource("DifferentPassword.fxml"));
            PanePasswordInvalid.getChildren().setAll(PanePasswordInvalidFXML);
            System.out.println("Contraseñas distintas");
        }
    }

    @FXML
    public void eventbtnCreateAccount() throws IOException {
        Registrar();
    }

    @FXML
    public void eventBtnSignIn() throws IOException {
        AnchorPane ventanaSignInFXML = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        VentanaSignUp.getChildren().setAll(ventanaSignInFXML);
    }
}