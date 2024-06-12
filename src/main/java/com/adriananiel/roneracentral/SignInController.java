package com.adriananiel.roneracentral;

import com.adriananiel.roneracentral.Clases.Usuario;
import com.adriananiel.roneracentral.Clases.ValidUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
    public TextField textGmail;
    @FXML
    public PasswordField textPassword;

    public void SignIn() throws IOException, ClassNotFoundException {
        String email = textGmail.getText();
        String password = textPassword.getText();
        Usuario usuario = null;
        try {
            ValidUser userManager = new ValidUser();
            usuario = userManager.valid(email, password);

        } catch (Exception ex) {
            System.out.println("error: usuario no encontrado");
        }
        if (email == null || email.isEmpty() || password == null || password.isEmpty()){
            System.out.println("Revisa tus credenciales" );

        }else {
            System.out.println("No estas en el sistema" );
        }
    }

    private void ajustarTamanoVentana() {
        // Obtener el Stage asociado a la escena de la vista actual
        Stage stage = (Stage) VentanaSignIn.getScene().getWindow();

        // Establecer el tamaño de la ventana
        stage.setWidth(1295);
        stage.setHeight(755);
    }

    @FXML
<<<<<<< Updated upstream
    public void eventSignIn (ActionEvent event) throws IOException {
        AnchorPane ventanaSignUpFXML = FXMLLoader.load(getClass().getResource("App.fxml"));
        VentanaSignIn.getChildren().setAll(ventanaSignUpFXML);

        // Ajustar el tamaño de la ventana al contenido de la vista
        ajustarTamanoVentana();
=======
    public void eventSignIn (ActionEvent event) throws IOException, ClassNotFoundException {
        SignIn();
>>>>>>> Stashed changes
        System.out.println("Has iniciado sesión");
    }

    @FXML
    protected void eventForgetPassword (ActionEvent event) throws IOException {
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