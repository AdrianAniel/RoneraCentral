package com.adriananiel.roneracentral;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

public class GestionarCuentaController implements Initializable {

    private String newPath;
    private String imagePath;

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
    @FXML
    private Pane PaneGestionarCuenta;

    public void cerrarAbrirVentana(){
        // Obtener el Stage desde el PaneGestionarCuenta
        Stage stage = (Stage) PaneGestionarCuenta.getScene().getWindow();
        stage.close(); // Cierra la ventana

        try {
            // Cargar el FXML de la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
            Parent root = loader.load(); // Esto carga el FXML y devuelve el nodo raíz
            SignInController controller = loader.getController(); // Obtiene el controlador de la vista cargada

            // Crear una nueva escena y mostrarla en una nueva ventana
            Stage nuevaVentana = new Stage();
            Scene escenaNuevaVentana = new Scene(root);
            nuevaVentana.setScene(escenaNuevaVentana);
            nuevaVentana.show();
        } catch (IOException e) {
            System.err.println("Error al cargar la nueva ventana: " + e.getMessage());
        }
    }

    private void eliminarImagenDeBaseDatos() {
        Platform.runLater(() -> {
            // Tu código aquí que modifica la UI
            File imageFile = new File(this.imagePath);
            if (imageFile.exists()) {
                boolean deleted = imageFile.delete();
                if (deleted) {
                    System.out.println("La imagen fue eliminada exitosamente.");
                    ImagenPerfil.setImage(null);
                } else {
                    System.out.println("Hubo un error al eliminar la imagen.");
                }
            } else {
                System.out.println("La imagen no existe en la ruta especificada.");
            }
        });
    }

    private void eliminarUsuarioDeBaseDatos() throws IOException {
        String username = UsuarioActualField.getText();
        Registro.eliminarUsuario(username);
    }

    public void cargarImagenPerfilDeBaseDatos(){
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
                    this.imagePath = userDetails[3]; // Esta es la dirección de la imagen

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

    public void limpiarCampos(){
        CambiarContrasenaField.clear();
        CambiarCorreoField.clear();
        CambiarUsuarioField.clear();
        UsuarioActualField.clear();
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

    public void examinarPonerFoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de imagen", "*.png", "*.jpg"));

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile!= null) {
            String originalPath = selectedFile.getAbsolutePath();
            System.out.println("Archivo seleccionado: " + originalPath);
            // Definir la nueva ruta donde se copiará la foto
            this.newPath = "BaseDatos/ImgUsuario/" + selectedFile.getName();

            // Copiar la foto a la nueva ubicación
            copyFile(originalPath, newPath);

            // Cargar la imagen desde la nueva ubicación
            try {
                BufferedImage bufferedImage = ImageIO.read(new File(newPath));
                WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
                System.out.println("Imagen cargada desde nueva ubicación: " + newPath);
                ImagenPerfil.setImage(image);
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
        String nuevaImagenDireccion = this.newPath;
        String nuevoRol = "";

        boolean actualizacionExitosa = Registro.actualizarUsuario(usernameActual, nuevoUsername, nuevaPassword, nuevoEmail, nuevaImagenDireccion, nuevoRol);

        if (actualizacionExitosa) {
            System.out.println("Nombre de usuario actualizado exitosamente.");
        } else {
            System.out.println("No se pudo actualizar el nombre de usuario.");
        }

        limpiarCampos();

    }
    @FXML
    void eventBtnEliminar(MouseEvent event) throws IOException {
        eliminarUsuarioDeBaseDatos();
        eliminarImagenDeBaseDatos();
        limpiarCampos();
        cerrarAbrirVentana();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarImagenPerfilDeBaseDatos();
    }
}