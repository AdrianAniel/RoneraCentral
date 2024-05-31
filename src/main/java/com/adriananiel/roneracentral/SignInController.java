package com.adriananiel.roneracentral;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {

    @FXML
    public Button btnSignIn;
    private Hyperlink ForgetPassword;
    public Hyperlink SignUp;

    @FXML
    public void eventSignIn (ActionEvent event) {
        System.out.println("Has iniciado sesión");
    }

    @FXML
    protected void eventForgetPassword (ActionEvent event) {
        System.out.println("Has presionado se me olvido lo contraseña");
    }

    public void eventSignUp(ActionEvent event) {
        System.out.println("Has presionado Sign Up");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}