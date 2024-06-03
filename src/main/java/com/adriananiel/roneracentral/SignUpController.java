package com.adriananiel.roneracentral;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class SignUpController {

    @FXML
    private Hyperlink btnSignIn;
    public AnchorPane VentanaSignUp;

    @FXML
    public void eventbtnCreateAccount() {
        System.out.println("Se creo la cuenta");
    }

    @FXML
    public void eventBtnSignIn(MouseEvent mouseEvent) throws IOException {
        AnchorPane ventanaSignInFXML = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        VentanaSignUp.getChildren().setAll(ventanaSignInFXML);
    }
}



