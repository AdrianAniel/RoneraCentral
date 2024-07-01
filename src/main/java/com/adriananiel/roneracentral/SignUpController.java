package com.adriananiel.roneracentral;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SignUpController {

    private static String imagePath;
    private static String urlImagen;

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
    @FXML
    private Button btnSignUp;
    @FXML
    private Button BtnAgregarImagen;
    @FXML
    private ImageView ImagenCrearCuenta;

    public void examinarPonerFoto(){
        FileChooser fileChooser = new FileChooser();
        // Puedes agregar más filtros si es necesario
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de imagen", "*.png", "*.jpg")); // Filtra por archivos de texto

        File selectedFile = fileChooser.showOpenDialog(null); // Pasamos null ya que estamos en un contexto de aplicación sin Stage
        if (selectedFile!= null) {
            imagePath = selectedFile.getAbsolutePath();
            System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());

            // Aquí pone la foto seleccionada con fileChooser en la UI que establecimos
            try {
                // Cargar la imagen utilizando ImageIO
                BufferedImage bufferedImage = ImageIO.read(selectedFile);
                WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
                ImagenCrearCuenta.setImage(image);
                System.out.println("Imagen cargada: " + image.getUrl());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void Registrar() throws IOException {
        String username = textUsername.getText();
        String password = textPassword.getText();
        String email = textGmail.getText();
        String confirmPassword = textConfirmPassword.getText();
        String imagenDireccion = imagePath;

        // Verificando si las contraseñas son iguales y entonces registra el usuario
        if (password.equals(confirmPassword)) {
            System.out.println("Contraseñas iguales");
            Usuario nuevoUsuario = new Usuario(username, password, email, imagenDireccion);
            Registro.agregarUsuario(nuevoUsuario);


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
    @FXML
    void eventBtnAgregarImagen(MouseEvent event) {
        examinarPonerFoto();
    }
}