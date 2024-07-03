package com.adriananiel.roneracentral;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    public void cerrarAbrirVentana(){
        // Obtener el Stage desde el PaneGestionarCuenta
        Stage stage = (Stage) VentanaSignIn.getScene().getWindow();
        stage.close(); // Cierra la ventana

        try {
            // Cargar el FXML de la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));
            Parent root = loader.load(); // Esto carga el FXML y devuelve el nodo raíz
            AppController controller = loader.getController(); // Obtiene el controlador de la vista cargada

            // Crear una nueva escena y mostrarla en una nueva ventana
            Stage nuevaVentana = new Stage();
            Scene escenaNuevaVentana = new Scene(root);
            nuevaVentana.setScene(escenaNuevaVentana);
            nuevaVentana.show();
        } catch (IOException e) {
            System.err.println("Error al cargar la nueva ventana: " + e.getMessage());
        }
    }

    public void iniciarCesion() throws IOException {
        Registro.cargarUsuariosDesdeArchivo();
        String username = textUsername.getText();
        String password = textPassword.getText();

        if (Login.iniciarSesion(username, password)) {
            System.out.println("Acceso concedido.");
            cerrarAbrirVentana();
        } else {
            System.out.println("No se pudo iniciar session");
            Pane UserPassIcorrectFXML = FXMLLoader.load(getClass().getResource("LoginMessenger.fxml"));
            LoginMessenger.getChildren().setAll(UserPassIcorrectFXML);
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
        Registro.cargarUsuariosDesdeArchivo();

    }
}