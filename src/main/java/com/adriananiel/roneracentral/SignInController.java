package com.adriananiel.roneracentral;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {

    @FXML
    public Button btnSignIn;
    @FXML
    private Hyperlink ForgetPassword;
    @FXML
    public Hyperlink SignUp;
    @FXML
    public AnchorPane VentanaSignIn;
    @FXML
    public TextField textUsername;
    @FXML
    public PasswordField textPassword;
    @FXML
    public Pane LoginMessenger;

    LoginSystem loginSystem = new LoginSystem();

    private void ajustarTamanoVentana() {
        // Obtener el Stage asociado a la escena de la vista actual
        Stage stage = (Stage) VentanaSignIn.getScene().getWindow();

        // Establecer el tamaño de la ventana
        stage.setWidth(1295);
        stage.setHeight(755);
    }

    public void iniciarCesion(){
        String usernameToCheck = textUsername.getText();
        String passwordToCheck = textPassword.getText();

        try {
            if (loginSystem.checkCredentials(usernameToCheck, passwordToCheck)) {
                ajustarTamanoVentana();
                AnchorPane ventanaAppFXML = FXMLLoader.load(getClass().getResource("App.fxml"));
                VentanaSignIn.getChildren().setAll(ventanaAppFXML);
            } else {
                Pane LoginMessengerFXML = FXMLLoader.load(getClass().getResource("LoginMessenger.fxml"));
                LoginMessenger.getChildren().setAll(LoginMessengerFXML);
            }
        } catch (IOException e) {
            System.err.println("Error al verificar el inicio de sesión: " + e.getMessage());
        }
    }

    @FXML
    public void eventSignIn (ActionEvent event) throws IOException {
        iniciarCesion();
    }

    @FXML
    protected void eventForgetPassword (ActionEvent event) {
        System.out.println("Has presionado se me olvido lo contraseña");
    }
    @FXML
    public void eventSignUp() throws IOException {
        AnchorPane ventanaSignUpFXML = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        VentanaSignIn.getChildren().setAll(ventanaSignUpFXML);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}