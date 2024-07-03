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
import java.nio.file.Files;

public class SignUpController {
    private static String newPath;

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

    // Método para copiar el archivo
    private void copyFile(String sourcePath, String destinationPath) {
        File sourceFile = new File(sourcePath);
        File destinationFile = new File(destinationPath);

        if (!sourceFile.exists()) {
            System.out.println("El archivo origen no existe.");
            return;
        }

        if (destinationFile.exists()) {
            System.out.println("El archivo de destino ya existe. Se sobrescribirá.");
        }

        try {
            // Leer el contenido del archivo origen
            byte[] content = Files.readAllBytes(sourceFile.toPath());
            // Escribir el contenido en el archivo de destino
            Files.write(destinationFile.toPath(), content);
            System.out.println("Archivo copiado exitosamente a: " + destinationPath);
        } catch (IOException e) {
            System.out.println("Error al copiar el archivo.");
            e.printStackTrace();
        }
    }

    public void examinarPonerFoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de imagen", "*.png", "*.jpg"));

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile!= null) {
            String originalPath = selectedFile.getAbsolutePath();
            System.out.println("Archivo seleccionado: " + originalPath);
            // Definir la nueva ruta donde se copiará la foto
            newPath = "BaseDatos/ImgUsuario/" + selectedFile.getName();

            // Copiar la foto a la nueva ubicación
            copyFile(originalPath, newPath);

            // Cargar la imagen desde la nueva ubicación
            try {
                BufferedImage bufferedImage = ImageIO.read(new File(newPath));
                WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
                System.out.println("Imagen cargada desde nueva ubicación: " + newPath);
                ImagenCrearCuenta.setImage(image);
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
        String imagenDireccion = newPath;

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