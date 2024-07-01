package com.adriananiel.roneracentral;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.scene.image.ImageView;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

public class GestionarCuentaController implements Initializable {

    private static String imagePath;

    @FXML
    private Button BtnCambiarFoto;

    @FXML
    private Button BtnEditar;

    @FXML
    private TextField CambiarContrasenaField;

    @FXML
    private TextField CambiarCorreoField;

    @FXML
    private TextField CambiarUsuarioField;

    @FXML
    private ImageView ImagenPerfil;
    @FXML
    private Button BtnEliminar;
    @FXML
    private TextField UsuarioActualField;

    public void limpiarCampos(){
        CambiarContrasenaField.clear();
        CambiarCorreoField.clear();
        CambiarUsuarioField.clear();
        UsuarioActualField.clear();
    }

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
                ImagenPerfil.setImage(image);
                System.out.println("Imagen cargada: " + image.getUrl());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void eventCambiarFoto(MouseEvent event) {
        examinarPonerFoto();
    }

    @FXML
    void eventEditar(MouseEvent event) throws IOException {
        String usernameActual = UsuarioActualField.getText();
        String nuevoUsername = CambiarUsuarioField.getText();
        String nuevaPassword = CambiarContrasenaField.getText();
        String nuevoEmail = CambiarCorreoField.getText();
        String nuevaImagenDireccion = imagePath;

        boolean actualizacionExitosa = Registro.actualizarUsuario(usernameActual, nuevoUsername, nuevaPassword, nuevoEmail, nuevaImagenDireccion);

        if (actualizacionExitosa) {
            System.out.println("Nombre de usuario actualizado exitosamente.");
        } else {
            System.out.println("No se pudo actualizar el nombre de usuario.");
        }

        limpiarCampos();

    }
    @FXML
    void eventBtnEliminar(MouseEvent event) throws IOException {
        String username = UsuarioActualField.getText();
        Registro.eliminarUsuario(username);
        limpiarCampos();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String loggedInUserName = AppUtil.getLoggedInUser();
        if (loggedInUserName!= null) {
            System.out.println("Bienvenido, " + loggedInUserName);
        }

        try {
            // Leer el archivo de texto línea por línea
            BufferedReader reader = new BufferedReader(new FileReader("BaseDatos/usuarios.txt"));
            String line;
            while ((line = reader.readLine())!= null) {
                // Parsear la línea para obtener los detalles del usuario
                String[] userDetails = line.split(",");
                String username = userDetails[0];

                // Comprobar si el nombre de usuario coincide con el del usuario que ha iniciado sesión
                if (username.equals(loggedInUserName)) { // Asumiendo que usernameIniciadoSesion está definido y actualizado correctamente
                    String imagePath = userDetails[3]; // Esta es la dirección de la imagen

                    // Si la ruta de la imagen no es nula, intentar cargar la imagen
                    if (imagePath!= null &&!imagePath.isEmpty()) {
                        try {
                            // Cargar la imagen utilizando ImageIO
                            BufferedImage bufferedImage = ImageIO.read(new File(imagePath));
                            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
                            ImagenPerfil.setImage(image);
                            break; // Salir del bucle ya que solo queremos cargar la imagen del usuario autenticado
                        } catch (IOException e) {
                            // Si hay un error al cargar la imagen, imprimir el mensaje "foto no encontrada"
                            System.out.println("Foto no encontrada");
                            break;
                        }
                    } else {
                        // Si la ruta de la imagen es nula o vacía, también imprimir el mensaje "foto no encontrada"
                        System.out.println("Foto no encontrada");
                        break;
                    }
                }
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
