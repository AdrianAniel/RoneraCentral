package com.adriananiel.roneracentral;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SignInController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}