package com.adriananiel.roneracentral;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {

    @FXML
    public Button btnSignIn;
    @FXML
    private Hyperlink ForgetPassword;
    public Hyperlink SignUp;
    public AnchorPane VentanaSignIn;

    @FXML
    public void eventSignIn (ActionEvent event) {
        System.out.println("Has iniciado sesión");
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