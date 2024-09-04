package com.adriananiel.roneracentral;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    private static String newPath;
    private String Rol;

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
    private ImageView ImagenCrearCuenta;
    @FXML
    private CheckBox CheckCalidad;
    @FXML
    private CheckBox CheckInventario;
    @FXML
    private CheckBox CheckProcesos;

    //metodo para activar los check cuando seleccionas otro a la vez
    public void selecionarDeseleccionarCheckBox(){
        CheckCalidad.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                CheckInventario.setSelected(false);
                CheckProcesos.setSelected(false);
            }
        });

        CheckInventario.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                CheckCalidad.setSelected(false);
                CheckProcesos.setSelected(false);
            }
        });

        CheckProcesos.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                CheckCalidad.setSelected(false);
                CheckInventario.setSelected(false);
            }
        });
    }

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

    //examina y pone la foto en la interfas desde la nueva ubicacion que es la base de datos
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

    //registra los usuarios
    public void Registrar() throws IOException {
        String username = textUsername.getText();
        String password = textPassword.getText();
        String email = textGmail.getText();
        String confirmPassword = textConfirmPassword.getText();
        String imagenDireccion = newPath;
        String rol = this.Rol;

        // Verificando si las contraseñas son iguales y entonces registra el usuario
        if (password.equals(confirmPassword)) {
            System.out.println("Contraseñas iguales");
            Usuario nuevoUsuario = new Usuario(username, password, email, imagenDireccion, rol);
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


    //evento que registra al usuario
    @FXML
    public void eventbtnCreateAccount() throws IOException {
        Registrar();
    }

    //evento que cambia a la vista de iniciar sesion
    @FXML
    public void eventBtnSignIn() throws IOException {
        AnchorPane ventanaSignInFXML = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        VentanaSignUp.getChildren().setAll(ventanaSignInFXML);
    }
    //evento que pone la imagen
    @FXML
    void eventBtnAgregarImagen(MouseEvent event) {

        examinarPonerFoto();
    }

    //los check que ponen el rol
    @FXML
    public void eventCheckCalidad(MouseEvent event) {
        this.Rol = "Calidad";
    }

    @FXML
    public void eventCheckInventario(MouseEvent event) {
        this.Rol = "Inventario";

    }

    @FXML
    public void eventCheckProcesos(MouseEvent event) {
        this.Rol = "Procesos";

    }

    //inicializador de la clase
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        selecionarDeseleccionarCheckBox();
    }
}